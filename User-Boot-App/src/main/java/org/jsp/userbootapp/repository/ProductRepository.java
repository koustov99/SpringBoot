package org.jsp.userbootapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("select p from Product p where p.brand=?1")
	public List<Product> findByBrand(String brand);

	@Query("select p from Product p where p.category=?1")
	public List<Product> findByCategory(String category);

	@Query("select p from Product p where p.user.id=?1")
	public List<Product> findByUserId(int id);

	@Query("select p from Product p where p.user.phone=?1 and p.user.password=?2")
	public Optional<Product> findByUserPhone(long phone, String password);
}
