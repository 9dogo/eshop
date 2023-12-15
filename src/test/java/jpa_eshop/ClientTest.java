package jpa_eshop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import eshop.services.ClientService;
import eshop.exceptions.ClientException;
import eshop.config.JpaConfig;
import jakarta.transaction.Transactional;
import eshop.model.Client;

@SpringJUnitConfig(JpaConfig.class)
@Transactional
@Rollback
class ClientTest {

	@Autowired
	ClientService ClientSrv;
	
	// static Client c = new Client("testNom", Title.M);
	
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
		Client client = new Client();
		assertNull(client.getId());
		client = ClientSrv.create(client);
		assertNotNull(client);
		assertNotNull(ClientSrv.findById(client.getId()));
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
		Client client = new Client();
		client = ClientSrv.create(client);
		System.out.println("cleint id "+client.getId());
		client.setName("test2");
		ClientSrv.update(client);
		assertEquals(ClientSrv.findById(client.getId()).getName(), "test2");
	}
}