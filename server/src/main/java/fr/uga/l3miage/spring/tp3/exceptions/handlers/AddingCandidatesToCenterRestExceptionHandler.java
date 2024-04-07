package fr.uga.l3miage.spring.tp3.exceptions.handlers;

import fr.uga.l3miage.spring.tp3.errors.AddCandidatesToCenterErrorResponse;
import fr.uga.l3miage.spring.tp3.exceptions.rest.AddingCandidatesToCenterRestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class AddingCandidatesToCenterRestExceptionHandler {

    @ExceptionHandler(AddingCandidatesToCenterRestException.class)
    public ResponseEntity<AddCandidatesToCenterErrorResponse> handle(HttpServletRequest httpServletRequest, Exception e){
        AddingCandidatesToCenterRestException exception = (AddingCandidatesToCenterRestException) e;
        final AddCandidatesToCenterErrorResponse response = AddCandidatesToCenterErrorResponse
                .builder()
                .uri(httpServletRequest.getRequestURI())
                .errorMessage(exception.getMessage())
                .build();
        log.warn(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
