package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
