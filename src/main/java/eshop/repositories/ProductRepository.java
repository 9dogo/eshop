package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
