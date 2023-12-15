package eshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.model.Category;
import project.exceptions.CategoryException;
import project.exceptions.CheckId;
import project.repositories.CategoryRepository;

@Service
//traitement disponible sur les categorys
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category create(Category category) {
		CheckId.checkIdNull(category.getId());
		return categoryRepository.save(category);
	}

	public Category findById(Long id) {
		CheckId.checkIdNotNull(id);
		return categoryRepository.findById(id).orElseThrow(() -> {throw new CategoryException("unable to find the Category with id "+id);});
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category update(Category category) {
		CheckId.checkIdNotNull(category.getId());
		return categoryRepository.save(category);
	}

	public void deleteById(Long id) {
		CheckId.checkIdNotNull(id);
		categoryRepository.delete(findById(id));
	}

	public void delete(Category category) {
		CheckId.checkIdNotNull(category.getId());
		deleteById(category.getId());
	}
}