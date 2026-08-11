package com.ticketing.exception;



import java.time.LocalDateTime;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;




@RestControllerAdvice
public class GlobalExceptionHandler {








    @ExceptionHandler(
            ResourceNotFoundException.class
    )
    public ResponseEntity<ApiErrorResponse> handleNotFound(
            ResourceNotFoundException exception,
            HttpServletRequest request
    ) {



        ApiErrorResponse response =
                new ApiErrorResponse(

                        LocalDateTime.now(),

                        HttpStatus.NOT_FOUND.value(),

                        exception.getMessage(),

                        request.getRequestURI()

                );



        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);

    }









    @ExceptionHandler(
            RuntimeException.class
    )
    public ResponseEntity<ApiErrorResponse> handleRuntimeException(
            RuntimeException exception,
            HttpServletRequest request
    ) {



        ApiErrorResponse response =
                new ApiErrorResponse(

                        LocalDateTime.now(),

                        HttpStatus.BAD_REQUEST.value(),

                        exception.getMessage(),

                        request.getRequestURI()

                );



        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);

    }









    @ExceptionHandler(
            Exception.class
    )
    public ResponseEntity<ApiErrorResponse> handleGeneralException(
            Exception exception,
            HttpServletRequest request
    ) {



        ApiErrorResponse response =
                new ApiErrorResponse(

                        LocalDateTime.now(),

                        HttpStatus.INTERNAL_SERVER_ERROR.value(),

                        "Something went wrong",

                        request.getRequestURI()

                );



        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);

    }


}