package org.jsp.userbootapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {


		@Query("select m from User m where m.name=?1")
		public List<User> findByName(String name);

		@Query("select m from User m where m.phone=?1")
		public Optional<User> findByPhone(long phone);

		@Query("select m from User m where m.email=?1")
		public Optional<User> findByEmail(String email);

		@Query("select m from User m where m.gst=?1")
		public Optional<User> findByGst(String gst);

		@Query("select m from User m where m.email=?1 and m.password=?2")
		public Optional<User> verifyByEmail(String email, String password);

		@Query("select m from User m where m.id=?1 and m.password=?2")
		public Optional<User> verifyById(int id, String password);

		@Query("select m from User m where m.gst=?1 and m.password=?2")
		public Optional<User> verifyByGst(String gst, String password);

		@Query("select m from User m where m.phone=?1 and m.password=?2")
		public Optional<User> verifyByPhone(long phone, String password);
	}
