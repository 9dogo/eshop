package jpa_eshop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import eshop.config.JpaConfig;
import eshop.exceptions.CategoryException;
import eshop.model.Category;
import eshop.services.CategoryService;
import jakarta.transaction.Transactional;

@SpringJUnitConfig(JpaConfig.class)
@Transactional
@Rollback
class CategoryTest {

	@Autowired
	CategoryService categorySrv;
	
	@Test
	void injectionTest() {
		assertNotNull(categorySrv);
	}
	
	@Test
	void recuperationCategoryServiceTest() {
		assertNotNull(categorySrv);
	}
	
	@Test
	@Commit
	void creatingCategoryTest() {
		Category category = new Category("test");
		assertNull(category.getId());
		category = categorySrv.create(category);
		assertNotNull(category);
		assertNotNull(categorySrv.findById(category.getId()));
	}
	
	@Test
	void CategoryExceptionTest() {
		assertThrows(CategoryException.class, () -> {
			categorySrv.findById(99999999L);
		});
	}
	
	@Test
	void findAllTest() {
		assertNotNull(categorySrv.findAll());
	}
	
	@Test
	void updateTest() {
		Category category = new Category();
		category = categorySrv.create(category);
		System.out.println("category id "+ category.getId());
		category.setName("test2");
		categorySrv.update(category);
		assertEquals(categorySrv.findById(category.getId()).getName(), "test2");
	}

}
