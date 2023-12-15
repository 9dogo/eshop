package jpa_eshop;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import eshop.config.JpaConfig;
import eshop.model.Product;
import eshop.model.Supplier;
import eshop.services.ProductService;
import eshop.services.SupplierService;
import jakarta.transaction.Transactional;

@SpringJUnitConfig(JpaConfig.class)
@Transactional
@Rollback
class SupplierTest {

	@Autowired
	SupplierService supplierService;
	@Autowired
	ProductService productService;
	

	@Test
	void injectionTest() {
		assertNotNull(supplierService);
	}
	
	@Test
	void findByNameTest() {
		Supplier s1=supplierService.create(new Supplier("alex"));
		
		assertEquals(s1, supplierService.findByName("alex"));
	}
	
	@Test
	@Commit
	void findByIdWithProductsTest() {
		Supplier s1=supplierService.create(new Supplier("alex"));
		Product p= productService.create(new Product("banane"));
		p.setSupplier(s1);
		productService.update(p);
		Supplier sTest=supplierService.findByIdWithProducts(s1.getId());
		assertEquals(sTest.getProducts().size(), 1);
		assertEquals(sTest.getProducts().get(0), p);
		
	}

}
