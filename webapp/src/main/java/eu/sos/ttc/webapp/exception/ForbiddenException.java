package eu.sos.ttc.webapp.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @author BauerMitFackel
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "You need to login first")
public class ForbiddenException extends RuntimeException {
}