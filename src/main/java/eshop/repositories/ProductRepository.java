package eshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.model.Category;
import eshop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByName(String name);
	List<Product> findByCategory(Category category); 
}
