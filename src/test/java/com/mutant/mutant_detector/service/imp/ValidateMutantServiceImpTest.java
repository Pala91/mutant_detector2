package com.mutant.mutant_detector.service.imp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ValidateMutantServiceImpTest {

    @InjectMocks
    private ValidateMutantServiceImp mutantService;

    @Test
    public void givenHorizontalDNAMutantSequence_thenReturnTrue() {

        String[] dna = {"ATGCAA", "CAGTGC", "TTTTGT", "AGAAGG", "CCCCTA", "TCACTG"};
        boolean mutant = mutantService.isMutant(dna);
        Assertions.assertTrue(mutant);
    }

    @Test
    public void givenVerticalDNAMutantSequence_thenReturnTrue() {

        String[] dna = {"GTGCGA", "CAGTGC", "TTGTGT", "AGAAGG", "CTCCTA", "TCACTG"};
        boolean mutant = mutantService.isMutant(dna);
        Assertions.assertTrue(mutant);
    }

    @Test
    public void givenForwardObliqueDNAMutantSequence_thenReturnTrue() {

        String[] dna = {"ATGCGA", "CAGTAC", "TTATGT", "AGAAGG", "CTCCTA", "TCACTG"};
        boolean mutant = mutantService.isMutant(dna);
        Assertions.assertTrue(mutant);
    }

    @Test
    public void givenBackwardForwardObliqueDNAMutantSequence_thenReturnTrue() {

        String[] dna = {"ATGCGA", "CTGTAC", "TTATCT", "AGACGG", "CTCCTA", "TCACTG"};
        boolean mutant = mutantService.isMutant(dna);
        Assertions.assertTrue(mutant);
    }

    @Test
    public void givenNoDNAMutantSequence_thenReturnFalse() {

        String[] dna = {"ATGCGA", "CGGTCC", "TTATGT", "AGAAGG", "CGCCTA", "TCACTG"};
        boolean mutant = mutantService.isMutant(dna);
        Assertions.assertFalse(mutant);
    }

    @Test()
    public void givenInvalidSequence_thenThrowException() {

        String[] dna = {"ATGCGA", "CGGTCC", "TTXYGT", "AGAATG", "CGCTTA", "TCTCTG"};

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            mutantService.isMutant(dna);
        });

    }

}
