package edu.devinc.readingRoom.rest;

import edu.devinc.readingRoom.exception.MyException;
import edu.devinc.readingRoom.exception.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler(MyException.class)
    public ResponseEntity<Response> handleException(MyException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

