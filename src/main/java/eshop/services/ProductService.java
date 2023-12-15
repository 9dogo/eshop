package eshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eshop.model.Category;
import eshop.model.Product;
import eshop.exceptions.ProductException;
import eshop.exceptions.CheckId;
import eshop.repositories.ProductRepository;

@Service
//traitement disponible sur les products
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product create(Product product) {
		CheckId.checkIdNull(product.getId());
		return productRepository.save(product);
	}

	public Product findById(Long id) {
		CheckId.checkIdNotNull(id);
		return productRepository.findById(id).orElseThrow(() -> {throw new ProductException("unable to find the Product with id "+id);});
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product update(Product product) {
		CheckId.checkIdNotNull(product.getId());
		return productRepository.save(product);
	}

	public void deleteById(Long id) {
		CheckId.checkIdNotNull(id);
		productRepository.delete(findById(id));
	}

	public void delete(Product product) {
		CheckId.checkIdNotNull(product.getId());
		deleteById(product.getId());
	}
	
	public Product findByname(String name) {
		if (name == null || name.isBlank()) {
			throw new ProductException("invalid name");
		}
		return productRepository.findByName(name);
	}
	
	public Product findByCategory(Category category) {
		if (category == null) {
			throw new ProductException("invalide category");
		}
		return productRepository.findByCategory(category);
	}
	
}