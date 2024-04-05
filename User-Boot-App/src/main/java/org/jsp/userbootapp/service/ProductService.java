package org.jsp.userbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dao.ProductDao;
import org.jsp.userbootapp.dao.UserDao;
import org.jsp.userbootapp.dto.Product;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.exception.IdNotFoundException;
import org.jsp.userbootapp.exception.InvalidCredentialsException;
import org.jsp.userbootapp.exception.ProductNotFoundException;
import org.jsp.userbootapp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao pDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int user_id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<User> dBUser = userDao.findById(user_id);
		if (dBUser.isPresent()) {
			User user = dBUser.get();
			user.getProducts().add(product);
			product.setUser(user);
			userDao.saveUser(user);
			structure.setData(pDao.saveProduct(product));
			structure.setMessage("Product added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new UserNotFoundException("Could Not Add Product as User is not Present");
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product p) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> recProduct = pDao.findById(p.getId());
		if (recProduct.isPresent()) {
			Product dbProduct = recProduct.get();
			dbProduct.setBrand(p.getBrand());
			dbProduct.setCategory(p.getCategory());
			dbProduct.setDescription(p.getDescription());
			dbProduct.setCost(p.getCost());
			dbProduct.setImg_url(p.getImg_url());
			dbProduct.setName(p.getName());
			structure.setData(pDao.saveProduct(dbProduct));
			structure.setMessage("Product Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		throw new ProductNotFoundException("Invalid Id");
	}

	public ResponseEntity<ResponseStructure<Product>> findProductById(int id) {
		Optional<Product> dBProduct = pDao.findById(id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (dBProduct.isPresent()) {
			structure.setData(dBProduct.get());
			structure.setMessage("Product Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid Id");
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts() {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(pDao.findAll());
		structure.setMessage("List of All Products");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteProduct(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Product> dbProduct = pDao.findById(id);
		if (dbProduct.isPresent()) {
			pDao.delete(id);
			structure.setMessage("Product Deleted");
			structure.setData("Product Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		structure.setMessage("Product Not Deleted");
		structure.setData("Product Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(String brand) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = pDao.findByBrand(brand);
		if (products.size() > 0) {
			structure.setData(products);
			structure.setMessage("Products Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid Brand");
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(String category) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = pDao.findByCategory(category);
		if (products.size() > 0) {
			structure.setData(products);
			structure.setMessage("Products Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid Catgeory");
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByUserId(int id) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = pDao.findByUserId(id);
		if (products.size() > 0) {
			structure.setData(products);
			structure.setMessage("Products Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid User Id");
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByUserPhone(long phone, String password) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		Optional<User> user = userDao.verifyUserByPhone(phone, password);
		if (user.isPresent()) {
			structure.setData(user.get().getProducts());
			structure.setMessage("Products Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid User Id");
	}
}