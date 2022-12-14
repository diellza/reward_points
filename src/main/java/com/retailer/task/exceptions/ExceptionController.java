package com.retailer.task.exceptions;

import com.retailer.task.domain.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/*
 * Controller for handling Exceptions.
*/
@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    private ResponseEntity<ErrorResponse> handleBadRequest(){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Bad request");
        errorResponse.setDetails("Check the url");
        errorResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.UnprocessableEntity.class)
    public ResponseEntity<ErrorResponse> handleUnprocessableEntity() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Unprocessable Entity");
        errorResponse.setDetails("Something went wrong processing the request, please try again later");
        errorResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Internal Server Error");
        errorResponse.setDetails("Something unexpected happened.");
        errorResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundError() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Not found");
        errorResponse.setDetails("There are no data found");
        errorResponse.setTimestamp(LocalDateTime.now());
        log.error("No data found in the db");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}

