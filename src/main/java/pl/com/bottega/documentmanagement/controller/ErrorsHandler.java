package pl.com.bottega.documentmanagement.controller;

import com.google.common.collect.Lists;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.com.bottega.documentmanagement.api.AuthRequiredException;

/**
 * Created by Piotr on 09.07.2016.
 */
@ControllerAdvice
public class ErrorsHandler {
    @ExceptionHandler(AuthRequiredException.class)
    public ResponseEntity handleAuthRequiredException(){
        MultiValueMap<String, String> m = new LinkedMultiValueMap<>();
        m.put("Content-Type", Lists.asList("application/json", new String[]{}));
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<String>(
                "{'error': authentication required'}",
                headers,
                HttpStatus.UNAUTHORIZED
        ) ;
    }
}
