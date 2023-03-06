package com.middleware.api.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.middleware.api.dto.ApiError;
import com.middleware.api.service.impl.CityExpressRateImpl;


import javassist.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ApiError> genericException(Exception ex, HttpServletRequest request) {

		logger.error("exception : " + ex.getLocalizedMessage() + " for " + request.getRequestURI());

		return new ResponseEntity<>(
				new ApiError(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),
						request.getRequestURI(), request.getMethod(), "Could not process request"),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({ com.middleware.api.config.exception.NotFoundException.class })
	public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {

		logger.error("Not found exception : " + ex.getLocalizedMessage() + " for " + request.getRequestURI());

		return new ResponseEntity<>(
				new ApiError(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND.toString(),
						request.getRequestURI(), request.getMethod(), "Could not process request"),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
	    BindingResult result = ex.getBindingResult();
	    List<FieldError> fieldErrors = result.getFieldErrors();
	    List<String> errors = new ArrayList();
	    for (FieldError fieldError : fieldErrors) {
	        errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
	    }
		return new ResponseEntity<>(
				new ApiError(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST.toString(),
						request.getRequestURI(), request.getMethod(), errors.toString()),
				HttpStatus.BAD_REQUEST);
//	    return new ApiError("400", errors.toArray());
	}
 
	

}
