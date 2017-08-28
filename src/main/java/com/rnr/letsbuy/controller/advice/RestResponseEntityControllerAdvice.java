package com.rnr.letsbuy.controller.advice;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rnr.letsbuy.exception.NoItemFoundException;
import com.rnr.letsbuy.exception.UserNotFoundException;
import com.rnr.letsbuy.model.error.ErrorMessage;

@ControllerAdvice
/*
 * We make sure that this value is less than that it is hold in GlobalControllerAdvice
 */
@Order(900)
public class RestResponseEntityControllerAdvice extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value={UserNotFoundException.class, NoItemFoundException.class})
	protected ResponseEntity<Object> handleNotFound(RuntimeException ex, HttpServletRequest request){
//	Instead of creating simple nodes with Jackson, we define a specific java model to describe a more detailes error message
//		ObjectMapper mapper = new ObjectMapper();
//		ObjectNode node = mapper.createObjectNode();
//		node.put("message", ex.getMessage());
		
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND.value(), new Date());
		errorMessage.setPath(request.getRequestURI());
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

}
