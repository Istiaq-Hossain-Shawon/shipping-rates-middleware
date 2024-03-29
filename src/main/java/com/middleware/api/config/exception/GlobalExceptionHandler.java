package com.middleware.api.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.middleware.api.response.ApiErrorResponse;

import javassist.NotFoundException;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ApiErrorResponse> genericException(Exception ex, HttpServletRequest request) {

		logger.error(MessageFormat.format("exception:{0} for {1}",ex.getLocalizedMessage(),request.getRequestURI()));

		return new ResponseEntity<>(
				new ApiErrorResponse(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),
						request.getRequestURI(), request.getMethod(), "Could not process request"),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public ResponseEntity<ApiErrorResponse> httpMessageNotReadableExceptionException(HttpMessageNotReadableException ex, HttpServletRequest request) {

		logger.error(MessageFormat.format("Invalid Parameters Exception:{0} for {1}",ex.getLocalizedMessage(),request.getRequestURI()));

		return new ResponseEntity<>(
				new ApiErrorResponse("JSON parse error:Invalid Parameter type", HttpStatus.BAD_REQUEST.toString(),
						request.getRequestURI(), request.getMethod(), "Invalid Parameters passed.Check the request body"),
				HttpStatus.BAD_REQUEST);
	}
	
 

	
	@ExceptionHandler({ com.middleware.api.config.exception.NotFoundException.class })
	public ResponseEntity<ApiErrorResponse> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {

		var exception=ex.getLocalizedMessage();
		var uri=request.getRequestURI();

		logger.error(MessageFormat.format("Not found exception:{0} for {1}",exception,uri));

		return new ResponseEntity<>(
				new ApiErrorResponse(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND.toString(),
						request.getRequestURI(), request.getMethod(), "Could not process request"),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
	    BindingResult result = ex.getBindingResult();
	    List<FieldError> fieldErrors = result.getFieldErrors();
	    List<String> errors = new ArrayList();
	    for (FieldError fieldError : fieldErrors) {
	        errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
	    }
		return new ResponseEntity<>(
				new ApiErrorResponse("Validation failed", HttpStatus.BAD_REQUEST.toString(),
						request.getRequestURI(), request.getMethod(), errors.toString()),
				HttpStatus.BAD_REQUEST);
	}
 
	

}
