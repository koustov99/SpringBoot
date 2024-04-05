package org.jsp.userbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public List<User> findByName(String name) {
		return userRepository.findByName(name);
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Optional<User> findByPhone(long phone) {
		return userRepository.findByPhone(phone);
	}

	public Optional<User> findByGst(String gst) {
		return userRepository.findByGst(gst);
	}

	public boolean deleteById(int id) {
		Optional<User> recUser = findById(id);
		if (recUser.isPresent()) {
			userRepository.delete(recUser.get());
			return true;
		}
		return false;
	}

	public Optional<User> verifyUserByPhone(long phone, String password) {
		return userRepository.verifyByPhone(phone, password);
	}

	public Optional<User> verifyUserById(int id, String password) {
		return userRepository.verifyById(id, password);
	}

	public Optional<User> verifyUserByEmail(String email, String password) {
		return userRepository.verifyByEmail(email, password);
	}

	public Optional<User> verifyUserByGst(String gst, String password) {
		return userRepository.verifyByGst(gst, password);
	}
}