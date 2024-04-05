package org.jsp.userbootapp.controller;

import java.util.List;

import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.service.UserService;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	

		@Autowired
		private UserService userService;

		@PostMapping
		@ResponseStatus(code = HttpStatus.CREATED)
		public ResponseStructure<User> saveUser(@RequestBody User user) {
			return userService.saveUser(user);
		}

		@PutMapping
		public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
			return userService.updateUser(user);
		}

		@GetMapping(value = "/{id}")
		public ResponseEntity<ResponseStructure<User>> findById(@PathVariable(name = "id") int id) {
			return userService.findById(id);
		}

		@DeleteMapping(value = "/{id}")
		public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable(name = "id") int id) {
			return userService.deleteById(id);
		}

		@GetMapping
		public ResponseEntity<ResponseStructure<List<User>>> findAll() {
			return userService.findAll();
		}

		@PostMapping("/verify-by-phone")
		public ResponseEntity<ResponseStructure<User>> verifyUser(@RequestParam long phone,
				@RequestParam String password) {
			return userService.verifyUser(phone, password);
		}

		@GetMapping("/find-by-name")
		public ResponseEntity<ResponseStructure<List<User>>> findByName(@RequestParam String name) {
			return userService.findByName(name);
		}

		@GetMapping("/find-by-phone")
		public ResponseEntity<ResponseStructure<User>> findByPhone(@RequestParam long phone) {
			return userService.findByPhone(phone);
		}

		@GetMapping("/find-by-email")
		public ResponseEntity<ResponseStructure<User>> findByEmail(@RequestParam String email) {
			return userService.findByEmail(email);
		}

		@GetMapping("/find-by-gst")
		public ResponseEntity<ResponseStructure<User>> findByGst(@RequestParam String gst) {
			return userService.findByGst(gst);
		}

		@PostMapping("/verify-by-email")
		public ResponseEntity<ResponseStructure<User>> verifyByEmail(@RequestParam String email,
				@RequestParam String password) {
			return userService.verifyByEmail(email, password);
		}

		@PostMapping("/verify-by-id")
		public ResponseEntity<ResponseStructure<User>> verifyById(@RequestParam int id, @RequestParam String password) {
			return userService.verifyById(id, password);
		}

		@PostMapping("/verify-by-gst")
		public ResponseEntity<ResponseStructure<User>> verifyByGst(@RequestParam String gst,
				@RequestParam String password) {
			return userService.verifyByGst(gst, password);
		}
	}

