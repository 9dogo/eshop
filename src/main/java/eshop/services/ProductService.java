package eshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.entities.Product;
import project.exceptions.ProductException;
import project.exceptions.CheckId;
import project.repositories.ProductRepository;

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
}