package com.mutant.mutant_detector.controler;

import com.mutant.mutant_detector.constants.Endpoint;
import com.mutant.mutant_detector.delegate.IValidateMutantDelegate;
import com.mutant.mutant_detector.dto.ValidationRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class findMutantController {

    private final IValidateMutantDelegate mutantDelegate;

    @Autowired
    public findMutantController(IValidateMutantDelegate mutantDelegate) {
        this.mutantDelegate = mutantDelegate;
    }

    @PostMapping(Endpoint.IS_MUTANT)
    public ResponseEntity validate(@RequestBody ValidationRq request) {

        mutantDelegate.isMutant(request.getDna());
        return ResponseEntity.ok().build();
    }

}
