package org.jsp.userbootapp.exception;

import org.jsp.userbootapp.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserBootAppExceptionHandler extends ResponseEntityExceptionHandler{
	
		@ExceptionHandler(UserNotFoundException.class)
		public ResponseEntity<ResponseStructure<String>> handleMNFE(UserNotFoundException exception) {
			ResponseStructure<String> structure = new ResponseStructure<>();
			structure.setMessage(exception.getMessage());
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setData("Invalid user Id");
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
		}

		@ExceptionHandler(ProductNotFoundException.class)
		public ResponseEntity<ResponseStructure<String>> handlePNFE(ProductNotFoundException exception) {
			ResponseStructure<String> structure = new ResponseStructure<>();
			structure.setMessage(exception.getMessage());
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setData("Product Not Found");
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
		}

		@ExceptionHandler(IdNotFoundException.class)
		public ResponseEntity<ResponseStructure<String>> handleINFE(IdNotFoundException exception) {
			ResponseStructure<String> structure = new ResponseStructure<>();
			structure.setMessage(exception.getMessage());
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setData("Id Not Found");
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
		}

		@ExceptionHandler(InvalidCredentialsException.class)
		public ResponseEntity<ResponseStructure<String>> handleICE(InvalidCredentialsException exception) {
			ResponseStructure<String> structure = new ResponseStructure<>();
			structure.setMessage(exception.getMessage());
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setData("Cannot Find User");
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
		}
	}

