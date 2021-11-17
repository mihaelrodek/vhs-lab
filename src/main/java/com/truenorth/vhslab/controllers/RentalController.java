package com.truenorth.vhslab.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/rental")
public class RentalController {


    @PostMapping
    public ResponseEntity<?> createRental() {
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    ;

    @GetMapping
    public ResponseEntity<?> getRental() {
        return (ResponseEntity<?>) ResponseEntity.ok();

    }

    @PutMapping
    public ResponseEntity<?> updateRental() {
        return (ResponseEntity<?>) ResponseEntity.ok();

    }

    @DeleteMapping
    public ResponseEntity<?> deleteRental() {
        return (ResponseEntity<?>) ResponseEntity.ok();

    }


}
