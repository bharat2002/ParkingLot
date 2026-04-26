const API = "/api";
const state = {
  lots: [],
  floors: [],
  spots: [],
  vehicles: [],
  activeTickets: [],
  entryPanels: [],
  exitPanels: [],
  selectedLotId: null,
  selectedFloorId: null
};

const $ = (selector) => document.querySelector(selector);
const $$ = (selector) => [...document.querySelectorAll(selector)];

async function request(path, options = {}) {
  const response = await fetch(`${API}${path}`, {
    headers: { "Content-Type": "application/json" },
    ...options
  });

  if (!response.ok) {
    let message = `${response.status} ${response.statusText}`;
    try {
      const body = await response.json();
      message = body.message || body.error || message;
    } catch {
      // The backend can return an empty 204 response for deletes.
    }
    throw new Error(message);
  }

  if (response.status === 204) return null;
  return response.json();
}

function toast(message, type = "success") {
  const el = $("#toast");
  el.textContent = message;
  el.className = `toast show ${type === "error" ? "error" : ""}`;
  window.clearTimeout(toast.timer);
  toast.timer = window.setTimeout(() => {
    el.className = "toast";
  }, 3200);
}

function option(value, label) {
  const el = document.createElement("option");
  el.value = value;
  el.textContent = label;
  return el;
}

function fillSelect(select, items, labeler, emptyLabel = "No records") {
  select.innerHTML = "";
  if (!items.length) {
    select.append(option("", emptyLabel));
    select.disabled = true;
    return;
  }
  select.disabled = false;
  items.forEach((item) => select.append(option(item.id, labeler(item))));
}

function selectedLot() {
  return state.lots.find((lot) => String(lot.id) === String(state.selectedLotId));
}

function selectedFloor() {
  return state.floors.find((floor) => String(floor.id) === String(state.selectedFloorId));
}

async function loadData(keepSelection = true) {
  const previousLot = keepSelection ? state.selectedLotId : null;
  const previousFloor = keepSelection ? state.selectedFloorId : null;

  state.lots = await request("/parkinglots");
  state.vehicles = await request("/vehicles");
  state.activeTickets = await request("/tickets/active");

  state.selectedLotId = previousLot && state.lots.some((lot) => String(lot.id) === String(previousLot))
    ? previousLot
    : state.lots[0]?.id ?? null;

  await loadLotDetails(previousFloor);
  render();
}

async function loadLotDetails(previousFloor = null) {
  if (!state.selectedLotId) {
    state.floors = [];
    state.spots = [];
    state.entryPanels = [];
    state.exitPanels = [];
    state.selectedFloorId = null;
    return;
  }

  const lotId = state.selectedLotId;
  const [floors, entryPanels, exitPanels] = await Promise.all([
    request(`/floors/by-lot/${lotId}`),
    request(`/entry-panels/by-lot/${lotId}`),
    request(`/exit-panels/by-lot/${lotId}`)
  ]);

  state.floors = floors;
  state.entryPanels = entryPanels;
  state.exitPanels = exitPanels;
  state.selectedFloorId = previousFloor && floors.some((floor) => String(floor.id) === String(previousFloor))
    ? previousFloor
    : floors[0]?.id ?? null;

  await loadFloorSpots();
}

async function loadFloorSpots() {
  if (!state.selectedFloorId) {
    state.spots = [];
    return;
  }
  state.spots = await request(`/spots/by-floor/${state.selectedFloorId}`);
}

function render() {
  renderSelectors();
  renderStats();
  renderSetupChecks();
  renderSpots();
  renderTickets();
}

function renderSelectors() {
  const lotSelect = $("#lotSelect");
  fillSelect(lotSelect, state.lots, (lot) => `${lot.name} #${lot.id}`, "Create a lot first");
  if (state.selectedLotId) lotSelect.value = state.selectedLotId;

  const floorSelect = $("#floorSelect");
  fillSelect(floorSelect, state.floors, (floor) => `Floor ${floor.floorNumber} #${floor.id}`, "Add a floor first");
  if (state.selectedFloorId) floorSelect.value = state.selectedFloorId;

  fillSelect($("#ticketVehicleSelect"), state.vehicles, (vehicle) => `${vehicle.licensePlate} - ${vehicle.vehicleType}`, "Register a vehicle first");
  fillSelect($("#entryPanelSelect"), state.entryPanels, (panel) => `${panel.panelCode} - ${panel.location}`, "Create an entry panel first");
  fillSelect($("#exitPanelSelect"), state.exitPanels, (panel) => `${panel.panelCode} - ${panel.location}`, "Create an exit panel first");
  fillSelect($("#exitTicketSelect"), state.activeTickets, ticketLabel, "No active tickets");

  const lot = selectedLot();
  $("#lotSummary").innerHTML = lot
    ? `<strong>${lot.name}</strong><br>${lot.address}<br>Selected floor: ${selectedFloor() ? selectedFloor().floorNumber : "none"}`
    : "No lot selected. Use Create Demo Data for a quick walkthrough.";
}

function renderStats() {
  const openSpots = state.spots.filter((spot) => spot.available).length;
  $("#lotCount").textContent = state.lots.length;
  $("#floorCount").textContent = state.floors.length;
  $("#openSpotCount").textContent = openSpots;
  $("#activeTicketCount").textContent = state.activeTickets.length;
}

function renderSetupChecks() {
  const checks = [
    ["Lot", state.lots.length > 0],
    ["Floor", state.floors.length > 0],
    ["Spots", state.spots.length > 0],
    ["Entry gate", state.entryPanels.length > 0],
    ["Exit gate", state.exitPanels.length > 0],
    ["Vehicle", state.vehicles.length > 0]
  ];

  $("#setupChecks").innerHTML = checks.map(([label, ready]) => `
    <div class="check-row ${ready ? "ready" : "missing"}">
      <span>${label}</span>
      <strong>${ready ? "Ready" : "Needed"}</strong>
    </div>
  `).join("");
}

function renderSpots() {
  const grid = $("#spotGrid");
  const floor = selectedFloor();
  $("#floorCaption").textContent = floor ? `Floor ${floor.floorNumber}` : "";

  if (!state.spots.length) {
    grid.innerHTML = `<div class="empty">No spots yet. Open Setup, add spots, or click Create Demo Data.</div>`;
    return;
  }

  grid.innerHTML = state.spots.map((spot) => `
    <article class="spot ${spot.available ? "open" : "taken"}">
      <strong>${spot.spotNumber}</strong>
      <span>${spot.spotType}</span>
      <span class="status">${spot.available ? "Available" : "Occupied"}</span>
    </article>
  `).join("");
}

function ticketLabel(ticket) {
  const vehicle = state.vehicles.find((item) => item.id === ticket.vehicleId);
  return `Ticket #${ticket.id}${vehicle ? ` - ${vehicle.licensePlate}` : ""}`;
}

function renderTickets() {
  const list = $("#activeTickets");
  $("#ticketCaption").textContent = state.activeTickets.length
    ? `${state.activeTickets.length} vehicle${state.activeTickets.length === 1 ? "" : "s"} parked`
    : "";

  if (!state.activeTickets.length) {
    list.innerHTML = `<div class="empty">No cars are parked yet. Register a vehicle and issue a ticket from Entry.</div>`;
    return;
  }

  list.innerHTML = state.activeTickets.map((ticket) => {
    const vehicle = state.vehicles.find((item) => item.id === ticket.vehicleId);
    return `
      <article class="ticket-row">
        <div>
          <strong>Ticket #${ticket.id}</strong>
          <small>${vehicle ? `${vehicle.licensePlate} - ${vehicle.vehicleType}` : `Vehicle #${ticket.vehicleId}`} - Spot #${ticket.spotId}</small>
        </div>
        <button class="primary-button quick-exit" data-ticket-id="${ticket.id}">Send to Exit</button>
      </article>
    `;
  }).join("");
}

function requireLot() {
  if (!state.selectedLotId) throw new Error("Create or seed a parking lot first.");
}

function requireFloor() {
  if (!state.selectedFloorId) throw new Error("Add a floor first.");
}

function formData(form) {
  return Object.fromEntries(new FormData(form).entries());
}

async function submitForm(form, handler) {
  try {
    await handler(formData(form), form);
    form.reset();
    await loadData(true);
  } catch (error) {
    toast(error.message, "error");
  }
}

async function seedDemo() {
  const lot = await request("/parkinglots", {
    method: "POST",
    body: JSON.stringify({ name: "Metro Hub Parking", address: "MG Road, Bangalore" })
  });

  state.selectedLotId = lot.id;
  const floors = [];
  for (const floorNumber of [1, 2]) {
    floors.push(await request("/floors", {
      method: "POST",
      body: JSON.stringify({ parkingLotId: lot.id, floorNumber })
    }));
  }

  await Promise.all([
    request("/entry-panels", { method: "POST", body: JSON.stringify({ parkingLotId: lot.id, panelCode: "EP-1", location: "Main Gate" }) }),
    request("/exit-panels", { method: "POST", body: JSON.stringify({ parkingLotId: lot.id, panelCode: "XP-1", location: "North Exit" }) })
  ]);

  const spotTypes = ["TWO_WHEELER", "CAR", "CAR", "LARGE", "ELECTRIC", "CAR"];
  for (const floor of floors) {
    await Promise.all(spotTypes.map((spotType, index) => request("/spots", {
      method: "POST",
      body: JSON.stringify({
        floorId: floor.id,
        spotNumber: `F${floor.floorNumber}-${spotType[0]}${String(index + 1).padStart(2, "0")}`,
        spotType
      })
    })));
  }

  await Promise.all([
    request("/vehicles", { method: "POST", body: JSON.stringify({ licensePlate: "KA01AB1234", ownerName: "Aarav Mehta", vehicleType: "CAR" }) }),
    request("/vehicles", { method: "POST", body: JSON.stringify({ licensePlate: "KA05EV2026", ownerName: "Mira Rao", vehicleType: "SUV" }) })
  ]);

  await loadData(false);
  state.selectedLotId = lot.id;
  state.selectedFloorId = floors[0].id;
  await loadLotDetails(state.selectedFloorId);
  render();
  toast("Demo data is ready.");
}

function bindEvents() {
  $("#refreshBtn").addEventListener("click", async () => {
    try {
      await loadData(true);
      toast("Data refreshed.");
    } catch (error) {
      toast(error.message, "error");
    }
  });

  $("#seedBtn").addEventListener("click", async () => {
    try {
      await seedDemo();
    } catch (error) {
      toast(error.message, "error");
    }
  });

  $("#heroSeedBtn").addEventListener("click", async () => {
    try {
      await seedDemo();
    } catch (error) {
      toast(error.message, "error");
    }
  });

  $("#lotSelect").addEventListener("change", async (event) => {
    state.selectedLotId = event.target.value;
    try {
      await loadLotDetails();
      render();
    } catch (error) {
      toast(error.message, "error");
    }
  });

  $("#floorSelect").addEventListener("change", async (event) => {
    state.selectedFloorId = event.target.value;
    try {
      await loadFloorSpots();
      render();
    } catch (error) {
      toast(error.message, "error");
    }
  });

  $$(".tab").forEach((tab) => {
    tab.addEventListener("click", () => {
      $$(".tab").forEach((item) => item.classList.toggle("active", item === tab));
      $$(".panel").forEach((panel) => panel.classList.toggle("active", panel.id === `${tab.dataset.tab}Panel`));
    });
  });

  $("#lotForm").addEventListener("submit", (event) => {
    event.preventDefault();
    submitForm(event.currentTarget, async (data) => {
      const lot = await request("/parkinglots", { method: "POST", body: JSON.stringify(data) });
      state.selectedLotId = lot.id;
      toast("Parking lot created.");
    });
  });

  $("#floorForm").addEventListener("submit", (event) => {
    event.preventDefault();
    submitForm(event.currentTarget, async (data) => {
      requireLot();
      const floor = await request("/floors", {
        method: "POST",
        body: JSON.stringify({ parkingLotId: Number(state.selectedLotId), floorNumber: Number(data.floorNumber) })
      });
      state.selectedFloorId = floor.id;
      toast("Floor added.");
    });
  });

  $("#spotForm").addEventListener("submit", (event) => {
    event.preventDefault();
    submitForm(event.currentTarget, async (data) => {
      requireFloor();
      await request("/spots", {
        method: "POST",
        body: JSON.stringify({ floorId: Number(state.selectedFloorId), spotNumber: data.spotNumber, spotType: data.spotType })
      });
      toast("Spot added.");
    });
  });

  $("#entryPanelForm").addEventListener("submit", (event) => {
    event.preventDefault();
    submitForm(event.currentTarget, async (data) => {
      requireLot();
      await request("/entry-panels", {
        method: "POST",
        body: JSON.stringify({ parkingLotId: Number(state.selectedLotId), panelCode: data.panelCode, location: data.location })
      });
      toast("Entry panel created.");
    });
  });

  $("#exitPanelForm").addEventListener("submit", (event) => {
    event.preventDefault();
    submitForm(event.currentTarget, async (data) => {
      requireLot();
      await request("/exit-panels", {
        method: "POST",
        body: JSON.stringify({ parkingLotId: Number(state.selectedLotId), panelCode: data.panelCode, location: data.location })
      });
      toast("Exit panel created.");
    });
  });

  $("#vehicleForm").addEventListener("submit", (event) => {
    event.preventDefault();
    submitForm(event.currentTarget, async (data) => {
      await request("/vehicles", { method: "POST", body: JSON.stringify(data) });
      toast("Vehicle registered.");
    });
  });

  $("#ticketForm").addEventListener("submit", (event) => {
    event.preventDefault();
    submitForm(event.currentTarget, async (data) => {
      requireFloor();
      if (!data.vehicleId || !data.entryPanelId) throw new Error("Vehicle and entry panel are required.");
      const ticket = await request("/tickets", {
        method: "POST",
        body: JSON.stringify({
          vehicleId: Number(data.vehicleId),
          floorId: Number(state.selectedFloorId),
          entryPanelId: Number(data.entryPanelId)
        })
      });
      toast(`Ticket #${ticket.id} issued.`);
    });
  });

  $("#exitTicketForm").addEventListener("submit", (event) => {
    event.preventDefault();
    submitForm(event.currentTarget, async (data) => {
      if (!data.ticketId || !data.exitPanelId) throw new Error("Ticket and exit panel are required.");
      const ticket = await request("/tickets/exit", {
        method: "POST",
        body: JSON.stringify({ ticketId: Number(data.ticketId), exitPanelId: Number(data.exitPanelId) })
      });
      $("#paymentTicketId").value = ticket.id;
      $("#receipt").innerHTML = `<strong>Ticket #${ticket.id}</strong><br><small>Fee calculated: Rs ${ticket.fee.toFixed(2)}</small>`;
      toast(`Fee calculated: Rs ${ticket.fee.toFixed(2)}`);
    });
  });

  $("#paymentForm").addEventListener("submit", (event) => {
    event.preventDefault();
    submitForm(event.currentTarget, async (data) => {
      const payment = await request("/payments", {
        method: "POST",
        body: JSON.stringify({ ticketId: Number(data.ticketId), mode: data.mode })
      });
      $("#receipt").innerHTML = `
        <strong>Payment #${payment.id}</strong><br>
        <small>Ticket #${payment.ticketId} - Rs ${payment.amount.toFixed(2)} - ${payment.mode} - ${payment.status}</small>
      `;
      toast("Payment completed.");
    });
  });

  $("#activeTickets").addEventListener("click", (event) => {
    const button = event.target.closest(".quick-exit");
    if (!button) return;
    $("#exitTicketSelect").value = button.dataset.ticketId;
    document.querySelector('[data-tab="payments"]').click();
  });
}

bindEvents();
loadData(false).catch((error) => {
  render();
  toast(error.message, "error");
});
