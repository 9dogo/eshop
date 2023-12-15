package jpa_eshop;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import eshop.services.ClientService;
import eshop.services.CommandService;
import eshop.exceptions.ClientException;
import eshop.exceptions.IdNotNullExcpetion;
import eshop.exceptions.IdNullExcpetion;
import eshop.config.JpaConfig;
import jakarta.transaction.Transactional;
import eshop.model.Client;
import eshop.model.Command;

@SpringJUnitConfig(JpaConfig.class)
@Transactional
@Rollback
class ClientTest {

	@Autowired
	ClientService clientSrv;
	@Autowired
	CommandService commandSrv;
	
	@Test
	void injectionTest() {
		assertNotNull(clientSrv);
	}
	
	@Test
	void recuperationClientSerficeTest() {
		assertNotNull(clientSrv);
	}
	
	@Test
	void creatingClientTest() {
		Client client = new Client();
		assertNull(client.getId());
		client = clientSrv.create(client);
		assertNotNull(client);
		assertNotNull(clientSrv.findById(client.getId()));
	}
	
	@Test
	void ClientExceptionTest() {
		assertThrows(ClientException.class, () -> {
			clientSrv.findById(99999999L);
		});
	}

	@Test
	void IdNotNullExceptionTest() {
		Client client = new Client();
		client.setId(1L);
		assertThrows(IdNotNullExcpetion.class, () -> { clientSrv.create(client); });
	}
	
	@Test
	void IdNullExceptionTestUpdate() {
		Client client = new Client();
		assertThrows(IdNullExcpetion.class, () -> { clientSrv.update(client); });
	}

	@Test
	void IdNullExceptionTestFindById() {
		Client client = new Client();
		assertThrows(IdNullExcpetion.class, () -> { clientSrv.findById(client.getId()); });
	}
	
	@Test
	void IdNullExceptionTestDeleteById() {
		Client client = new Client();
		assertThrows(IdNullExcpetion.class, () -> { clientSrv.deleteById(client.getId()); });
	}
	
	@Test
	void IdNullExceptionTestFindByIdWithCommand() {
		Client client = new Client();
		assertThrows(IdNullExcpetion.class, () -> { clientSrv.findByIdWithCommand(client.getId()); });
	}
	
	@Test
	void findAllTest() {
		assertNotNull(clientSrv.findAll());
	}
	
	@Test
	void updateTest() {
		Client client = new Client();
		client = clientSrv.create(client);
		client.setName("test2");
		clientSrv.update(client);
		assertEquals(clientSrv.findById(client.getId()).getName(), "test2");
	}

	@Test
	void deleteTest() {
		Client client = new Client();
		client = clientSrv.create(client);
		clientSrv.delete(client);

		assertEquals(0, clientSrv.findAll().size());
	}

	@Test
	void findByName() {
		Client client = new Client();
		client.setName("jerry");
		client = clientSrv.create(client);
		List<Client> res = clientSrv.findByName(client.getName());
		assertEquals(1, res.size());
		assertEquals(client, res.get(0));
	}
	@Test
	void findByFirstName() {
		Client client = new Client();
		client.setFirstName("jerry");
		client = clientSrv.create(client);
		List<Client> res = clientSrv.findByFirstName(client.getFirstName());
		assertEquals(1, res.size());
		assertEquals(client, res.get(0));
	}
	@Test
	void findByNameAndFirstName() {
		Client client = new Client();
		client.setFirstName("jerry");
		client.setName("smith");
		client = clientSrv.create(client);
		List<Client> res = clientSrv.findByNameAndFirstName(client.getName(), client.getFirstName());
		assertEquals(1, res.size());
		assertEquals(client, res.get(0));
	}

	// @Test
	// void findByNameAndFirstNameInvalidName() {
	// 	Client client = new Client();
	// 	client.setFirstName("jerry");
	// 	// client.setName("smith");
	// 	client = clientSrv.create(client);
	// 	assertEquals(ClientException.class, clientSrv.findByNameAndFirstName(client.getName(), client.getFirstName()));
	// }
	// @Test
	// void findByNameAndFirstNameInvalidFirstName() {
	// 	Client client = new Client();
	// 	// client.setFirstName("jerry");
	// 	client.setName("smith");
	// 	client = clientSrv.create(client);
	// 	assertEquals(ClientException.class, clientSrv.findByNameAndFirstName(client.getName(), client.getFirstName()));
	// }

	@Test
	void findByIdFetchCommand() {
		Client client = new Client();
		client = clientSrv.create(client);

		Command command = new Command();
		command.setClient(client);
		
		client.addCommand(command);
		commandSrv.create(command);

		Client res = clientSrv.findByIdWithCommand(client.getId());
		assertEquals(client, res); 
		assertEquals(1, client.getCommands().size());
		assertEquals(command, client.getCommands().get(0)); 
	}
}