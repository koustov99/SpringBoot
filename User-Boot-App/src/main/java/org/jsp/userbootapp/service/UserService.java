package org.jsp.userbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dao.UserDao;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.exception.IdNotFoundException;
import org.jsp.userbootapp.exception.InvalidCredentialsException;
import org.jsp.userbootapp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
		@Autowired
		private UserDao userDao;

		public ResponseStructure<User> saveUser(User user) {
			ResponseStructure<User> structure = new ResponseStructure<>();
			structure.setMessage("User Saved");
			structure.setData(userDao.saveUser(user));
			structure.setStatusCode(HttpStatus.CREATED.value());
			return structure;
		}

		public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
			Optional<User> recUser = userDao.findById(user.getId());
			ResponseStructure<User> structure = new ResponseStructure<>();
			if (recUser.isPresent()) {
				User dbUser = new User();
				dbUser.setId(user.getId());
				dbUser.setName(user.getName());
				dbUser.setPhone(user.getPhone());
				dbUser.setEmail(user.getEmail());
				dbUser.setGst(user.getGst());
				dbUser.setPassword(user.getPassword());
				structure.setMessage("User Updated Successfully");
				structure.setData(userDao.saveUser(user));
				structure.setStatusCode(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
			}
			throw new UserNotFoundException("User Id Not Found");
		}

		public ResponseEntity<ResponseStructure<User>> findById(int id) {
			Optional<User> recUser = userDao.findById(id);
			ResponseStructure<User> structure = new ResponseStructure<>();
			if (recUser.isPresent()) {
				structure.setMessage("User Found");
				structure.setData(recUser.get());
				structure.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
			}
			throw new UserNotFoundException("User Id Not Found");
		}

		public ResponseEntity<ResponseStructure<String>> deleteById(int id) {
			Optional<User> recUser = userDao.findById(id);
			ResponseStructure<String> structure = new ResponseStructure<>();
			if (recUser.isPresent()) {
				structure.setMessage("User Found");
				structure.setData("User Deleted");
				structure.setStatusCode(HttpStatus.OK.value());
				userDao.deleteById(id);
				return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
			}
			structure.setMessage("User Not Found");
			structure.setData(null);
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
		}

		public ResponseEntity<ResponseStructure<List<User>>> findAll() {
			ResponseStructure<List<User>> structure = new ResponseStructure<>();
			List<User> users = userDao.findAll();
			structure.setData(users);
			if (users.size() > 0) {
				structure.setMessage("User Saved");
				structure.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
			}
			structure.setMessage("User Not Found");
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.NOT_FOUND);
		}

		public ResponseEntity<ResponseStructure<User>> verifyUser(long phone, String password) {
			Optional<User> recUser = userDao.verifyUserByPhone(phone, password);
			ResponseStructure<User> structure = new ResponseStructure<>();
			if (recUser.isPresent()) {
				structure.setMessage("User Verified");
				structure.setData(recUser.get());
				structure.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
			}
			throw new InvalidCredentialsException("Invalid Phone Or Password");
		}

		public ResponseEntity<ResponseStructure<List<User>>> findByName(String name) {
			ResponseStructure<List<User>> structure = new ResponseStructure<>();
			List<User> users = userDao.findByName(name);
			structure.setData(users);
			if (users.size() > 0) {
				structure.setMessage("User Saved");
				structure.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
			}
			throw new InvalidCredentialsException("Invalid Name");
		}

		public ResponseEntity<ResponseStructure<User>> findByPhone(long phone) {
			Optional<User> recUser = userDao.findByPhone(phone);
			ResponseStructure<User> structure = new ResponseStructure<>();
			if (recUser.isPresent()) {
				structure.setMessage("User Found");
				structure.setData(recUser.get());
				structure.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
			}
			throw new UserNotFoundException("User Phone Not Found");
		}

		public ResponseEntity<ResponseStructure<User>> findByEmail(String email) {
			Optional<User> recUser = userDao.findByEmail(email);
			ResponseStructure<User> structure = new ResponseStructure<>();
			if (recUser.isPresent()) {
				structure.setMessage("User Found");
				structure.setData(recUser.get());
				structure.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
			}
			throw new UserNotFoundException("User Email Not Found");
		}

		public ResponseEntity<ResponseStructure<User>> findByGst(String gst) {
			Optional<User> recUser = userDao.findByGst(gst);
			ResponseStructure<User> structure = new ResponseStructure<>();
			if (recUser.isPresent()) {
				structure.setMessage("User Found");
				structure.setData(recUser.get());
				structure.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
			}
			throw new UserNotFoundException("User Gst Not Found");
		}

		public ResponseEntity<ResponseStructure<User>> verifyByEmail(String email, String password) {
			Optional<User> recUser = userDao.verifyUserByEmail(email, password);
			ResponseStructure<User> structure = new ResponseStructure<>();
			if (recUser.isPresent()) {
				structure.setMessage("User Verified");
				structure.setData(recUser.get());
				structure.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
			}
			throw new InvalidCredentialsException("Invalid Email Or Password");
		}

		public ResponseEntity<ResponseStructure<User>> verifyById(int id, String password) {
			Optional<User> recUser = userDao.verifyUserById(id, password);
			ResponseStructure<User> structure = new ResponseStructure<>();
			if (recUser.isPresent()) {
				structure.setMessage("User Verified");
				structure.setData(recUser.get());
				structure.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
			}
			throw new InvalidCredentialsException("Invalid Id Or Password");
		}

		public ResponseEntity<ResponseStructure<User>> verifyByGst(String gst, String password) {
			Optional<User> recUser = userDao.verifyUserByGst(gst, password);
			ResponseStructure<User> structure = new ResponseStructure<>();
			if (recUser.isPresent()) {
				structure.setMessage("User Verified");
				structure.setData(recUser.get());
				structure.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
			}
			throw new InvalidCredentialsException("Invalid Gst Or Password");
		}
	}