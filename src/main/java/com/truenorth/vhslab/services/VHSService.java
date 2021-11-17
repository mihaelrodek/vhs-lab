package com.truenorth.vhslab.services;

import com.truenorth.vhslab.exceptions.ValidationException;
import com.truenorth.vhslab.entities.VHS;
import com.truenorth.vhslab.repositories.VhsRepository;
import com.truenorth.vhslab.request.CreateVhsRequest;
import com.truenorth.vhslab.responses.ErrorType;
import com.truenorth.vhslab.responses.RegistrationField;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VHSService {

    private final VhsRepository vhsRepository;

    public VHSService(VhsRepository vhsRepository) {
        this.vhsRepository = vhsRepository;
    }

    public VHS createVhs(CreateVhsRequest createVhsRequest) throws ValidationException {
        validate(createVhsRequest.getTitle(), createVhsRequest.getYear());
        VHS vhs = new VHS();

        vhs.setTitle(createVhsRequest.getTitle());
        vhs.setYear(createVhsRequest.getYear());

        return vhsRepository.save(vhs);
    }

    private void validate(String title, Long year) throws ValidationException {
        if (!title.toUpperCase().matches("^[A-Za-z ]{1,20}$")) {
            throw new ValidationException("Neispravan format imena.", RegistrationField.USERNAME, ErrorType.INVALID_FORMAT);
        }
        if (year != null && (year < 1900 || year > 2099)) {
            throw new ValidationException("Neispravan format godine.", RegistrationField.PASSWORD, ErrorType.INVALID_FORMAT);
        }
    }

    public ResponseEntity<VHS> getVHSById(Long id) {

        Optional<VHS> result = vhsRepository.findById(id);

        if (result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        VHS vhs = result.get();
        return new ResponseEntity<>(vhs, HttpStatus.OK);
    }


}
