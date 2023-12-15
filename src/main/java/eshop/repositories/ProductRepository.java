package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
