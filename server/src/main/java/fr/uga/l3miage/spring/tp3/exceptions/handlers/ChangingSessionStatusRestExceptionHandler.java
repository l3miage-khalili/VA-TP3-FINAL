package fr.uga.l3miage.spring.tp3.exceptions.handlers;

import fr.uga.l3miage.spring.tp3.errors.ChangeSessionStatusErrorResponse;
import fr.uga.l3miage.spring.tp3.exceptions.rest.ChangingSessionStatusRestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ChangingSessionStatusRestExceptionHandler {
    public ResponseEntity<ChangeSessionStatusErrorResponse> handle(HttpServletRequest httpServletRequest, Exception e){
        ChangingSessionStatusRestException exception = (ChangingSessionStatusRestException) e;
        final ChangeSessionStatusErrorResponse response = ChangeSessionStatusErrorResponse
                .builder()
                .uri(httpServletRequest.getRequestURI())
                .errorMessage(exception.getMessage())
                .build();
        log.warn(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

}
