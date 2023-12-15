package jpa_eshop;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import eshop.services.ClientService;
import eshop.exceptions.ClientException;
import eshop.config.JpaConfig;
import jakarta.transaction.Transactional;
import eshop.model.Title;
import eshop.model.Client;


@SpringJUnitConfig(JpaConfig.class)
@Transactional
@Rollback
class ClientTest {

	@Autowired
	ClientService ClientSrv;
	
	static Client c = new Client("testNom", Title.M);
	
	@Test
	void injectionTest() {
		assertNotNull(ClientSrv);
	}
	
	@Test
	void recuperationClientSerficeTest() {
		assertNotNull(ClientSrv);
	}
	
	@Test
	@Commit
	void creatingClientTest() {
		assertNull(c.getId());
		c = ClientSrv.create(c);
		assertNotNull(c);
		assertNotNull(ClientSrv.findById(c.getId()));
	}
	
	@Test
	void ClientExceptionTest() {
		assertThrows(ClientException.class, () -> {
			ClientSrv.findById(99999999L);
		});
	}
	
	@Test
	void findAllTest() {
		assertNotNull(ClientSrv.findAll());
	}
	
	@Test
	void updateTest() {
		Client client = ClientSrv.findById(c.getId());
		client.setName("test2");
		ClientSrv.update(client);
		assertEquals(ClientSrv.findById(c.getId()).getName(), "test2");
	}
	
	
	
}
