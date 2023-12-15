package jpa_eshop;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import eshop.config.JpaConfig;
import eshop.exceptions.ClientException;
import eshop.exceptions.ProductException;
import eshop.model.Category;
import eshop.model.Client;
import eshop.model.Product;
import eshop.services.CategoryService;
import eshop.services.ProductService;
import jakarta.transaction.Transactional;

@SpringJUnitConfig(JpaConfig.class)
@Transactional
@Rollback
class ProductTest {
	
	@Autowired
	ProductService productserv;
	
	@Autowired
	CategoryService catserv;
	
	@Test
	void injectionTest() {
		assertNotNull(productserv);
	}

	@Test
	void recuperationProductServiceTest() {
		assertNotNull(productserv);
	}
	
	@Test
	void creatingProductTest() {
		Product product = new Product();
		assertNull(product.getId());
		product = productserv.create(product);
		assertNotNull(product);
		assertNotNull(productserv.findById(product.getId()));
	}
	
	@Test
	void ProductExceptionTest() {
		assertThrows(ProductException.class, () -> {
			productserv.findById(99999999L);
		});
	}
	
	@Test
	void findAllTest() {
		assertNotNull(productserv.findAll());
	}
	
	@Test
	void updateTest() {
		Product product = new Product();
		product = productserv.create(product);
		System.out.println("product id "+product.getId());
		product.setName("test2");
		productserv.update(product);
		assertEquals(productserv.findById(product.getId()).getName(), "test2");
	}
	
	@Test
	void deleteTest() {
		Product product = new Product();
		product = productserv.create(product);
		productserv.delete(product);
		assertEquals(0, productserv.findAll().size());
	}
	
	@Test
	void deleteByIdTest() {
		Product product = new Product();
		product = productserv.create(product);
		productserv.deleteById(product.getId());
		assertEquals(0, productserv.findAll().size());
	}
	@Test
	void findByNameTest() {
		Product product = new Product();
		product = productserv.create(product);
		System.out.println("product id "+product.getId());
		product.setName("test2");
		productserv.update(product);
		List<Product> pro = productserv.findByname("test2");
		assertEquals(1, pro.size());
		assertEquals(product, pro.get(0));
	}
	
	@Test
	void findByCategoryTest() {
		Category cat = new Category("Test");
		catserv.create(cat);
		Product product = new Product();
		product = productserv.create(product);
		System.out.println("product id "+product.getId());
		product.setCategory(cat);
		productserv.update(product);
		List<Product> pro = productserv.findByCategory(cat);
		assertEquals(1, pro.size());
		assertEquals(product, pro.get(0));
	}
	
	
}

