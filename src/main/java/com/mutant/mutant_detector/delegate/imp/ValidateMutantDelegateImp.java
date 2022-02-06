package com.mutant.mutant_detector.delegate.imp;

import com.mutant.mutant_detector.delegate.IValidateMutantDelegate;
import com.mutant.mutant_detector.exception.NoMutantException;
import com.mutant.mutant_detector.service.IValidateMutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ValidateMutantDelegateImp implements IValidateMutantDelegate {

    private final IValidateMutantService validateMutantService;

    @Autowired
    public ValidateMutantDelegateImp(IValidateMutantService validateMutantService) {
        this.validateMutantService = validateMutantService;
    }

    @Override
    public void isMutant(String[] dna) {

        boolean isMutant = validateMutantService.isMutant(Arrays.stream(dna).
                map(String::toUpperCase).toArray(String[]::new));

        if(!isMutant){
            throw new NoMutantException();
        }

    }
}
