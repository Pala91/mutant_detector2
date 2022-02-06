package com.mutant.mutant_detector.service.imp;

import com.mutant.mutant_detector.service.IValidateMutantService;
import org.springframework.stereotype.Service;

@Service
public class ValidateMutantServiceImp implements IValidateMutantService {

    private static final int TOTAL_AMOUNT = 4;

    private static final String VALID_REGEX = "^[ATGC]+$";

    @Override
    public boolean isMutant(String[] dna) {
        int rows = dna.length;
        int columns = dna[0].length();

        String[][] dnaMatrix = createDnaMatrix(dna, rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (j + TOTAL_AMOUNT < columns) {
                    if (validateHorizontal(dnaMatrix[i], j, 1)) {
                        return true;
                    }
                }
                if (i + TOTAL_AMOUNT < rows) {
                    if (validateVertical(dnaMatrix, i, j, 1)) {
                        return true;
                    }
                }
                if (j + TOTAL_AMOUNT < columns && i + TOTAL_AMOUNT < rows) {
                    if (validateForwardOblique(dnaMatrix, i, j, 1)) {
                        return true;
                    }
                }
                if (j - TOTAL_AMOUNT >= 0 && i + TOTAL_AMOUNT <= rows) {
                    if (validateBackwardOblique(dnaMatrix, i, j, 1)) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    private String[][] createDnaMatrix(String[] dna, int rows, int columns) {
        String[][] matrix = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            if (!dna[i].matches(VALID_REGEX)) {
                throw new RuntimeException();
            }
            matrix[i] = dna[i].toLowerCase().split("");
        }
        return matrix;
    }

    private boolean validateHorizontal(String[] dnaRow, int index, int amount) {
        if (amount == TOTAL_AMOUNT) {
            return true;
        }
        if (dnaRow[index].equals(dnaRow[index + 1])) {
            return validateHorizontal(dnaRow, index + 1, amount + 1);
        }
        return false;
    }

    private boolean validateVertical(String[][] dnaMatrix, int x, int y, int amount) {

        if (amount == TOTAL_AMOUNT) {
            return true;
        }
        if (dnaMatrix[x][y].equals(dnaMatrix[x + 1][y])) {
            return validateVertical(dnaMatrix, x + 1, y, amount + 1);
        }
        return false;
    }

    private boolean validateForwardOblique(String[][] dnaMatrix, int x, int y, int amount) {

        if (amount == TOTAL_AMOUNT) {
            return true;
        }
        if (dnaMatrix[x][y].equals(dnaMatrix[x + 1][y + 1])) {
            return validateForwardOblique(dnaMatrix, x + 1, y + 1, amount + 1);
        }
        return false;
    }

    private boolean validateBackwardOblique(String[][] dnaMatrix, int x, int y, int amount) {

        if (amount == TOTAL_AMOUNT) {
            return true;
        }
        if (dnaMatrix[x][y].equals(dnaMatrix[x + 1][y - 1])) {
            return validateBackwardOblique(dnaMatrix, x + 1, y - 1, amount + 1);
        }
        return false;
    }

}
