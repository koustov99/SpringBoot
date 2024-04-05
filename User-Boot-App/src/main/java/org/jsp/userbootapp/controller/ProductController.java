package org.jsp.userbootapp.controller;

import java.util.List;

import org.jsp.userbootapp.dto.Product;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
		@Autowired
		private ProductService service;

		@PostMapping("/{user_id}")
		public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product p,
				@PathVariable int user_id) {
			return service.saveProduct(p, user_id);
		}

		@PutMapping
		public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product p) {
			return service.updateProduct(p);
		}

		@GetMapping("/{id}")
		public ResponseEntity<ResponseStructure<Product>> findProductById(@PathVariable int id) {
			return service.findProductById(id);
		}

		@GetMapping
		public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts() {
			return service.findAllProducts();
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable int id) {
			return service.deleteProduct(id);
		}

		@GetMapping("/find-by-brand/{brand}")
		public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(@PathVariable String brand) {
			return service.findByBrand(brand);
		}

		@GetMapping("/find-by-category/{category}")
		public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(@PathVariable String category) {
			return service.findByCategory(category);
		}

		@GetMapping("/find-by-user/{user_id}")
		public ResponseEntity<ResponseStructure<List<Product>>> findByUserId(@PathVariable int user_id) {
			return service.findByUserId(user_id);
		}

		@PostMapping("/find-by-user-phone")
		public ResponseEntity<ResponseStructure<List<Product>>> findByUserPhone(@RequestParam long phone,
				@RequestParam String password) {
			return service.findByUserPhone(phone, password);
		}
	


}
