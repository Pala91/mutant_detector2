package com.mutant.mutant_detector.controler.advisor;

import com.mutant.mutant_detector.exception.NoMutantException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NoMutantException.class)
    public ResponseEntity handleNoMutantException(NoMutantException exception){

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
