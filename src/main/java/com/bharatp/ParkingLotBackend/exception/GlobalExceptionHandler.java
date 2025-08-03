package com.bharatp.ParkingLotBackend.exception;

import com.bharatp.ParkingLotBackend.dto.error.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFound(NotFoundException ex) {
        ErrorDTO err = new ErrorDTO("NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(404).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleOther(Exception ex) {
        ErrorDTO err = new ErrorDTO("INTERNAL_ERROR", ex.getMessage());
        return ResponseEntity.status(500).body(err);
    }
}