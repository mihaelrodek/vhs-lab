package com.truenorth.vhslab.controllers;

import com.truenorth.vhslab.exceptions.ValidationException;
import com.truenorth.vhslab.entities.VHS;
import com.truenorth.vhslab.request.CreateVhsRequest;
import com.truenorth.vhslab.services.VHSService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vhs")
public class VHSController {

    private final VHSService vhsService;

    public VHSController(VHSService vhsService) {
        this.vhsService = vhsService;
    }

    @PostMapping("/create")
    public ResponseEntity<VHS> createVhs(@RequestBody CreateVhsRequest createVhsRequest) throws ValidationException {
        vhsService.createVhs(createVhsRequest);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<VHS> getUser(@PathVariable Long id){
        return vhsService.getVHSById(id);
    }


}
