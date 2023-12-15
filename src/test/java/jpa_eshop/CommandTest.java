package jpa_eshop;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import eshop.config.JpaConfig;
import eshop.exceptions.CommandException;
import eshop.exceptions.IdNotNullExcpetion;
import eshop.exceptions.IdNullExcpetion;
import eshop.model.Command;
import eshop.services.CommandService;
import jakarta.transaction.Transactional;

@SpringJUnitConfig(JpaConfig.class)
@Transactional
@Rollback
class CommandTest {

	@Autowired
	CommandService commandSrv;
	
	@Test
	void injectionTest() {
		assertNotNull(commandSrv);
	}
	
	@Test
	void recuperationCommandSerficeTest() {
		assertNotNull(commandSrv);
	}
	
	@Test
	void creatingCommandTest() {
		Command command = new Command();
		assertNull(command.getId());
		command = commandSrv.create(command);
		assertNotNull(command);
		assertNotNull(commandSrv.findById(command.getId()));
	}

	@Test
	void IdNotNullExceptionTest() {
		Command command = new Command();
		command.setId(1L);
		assertThrows(IdNotNullExcpetion.class, () -> { commandSrv.create(command); });
	}
	
	@Test
	void IdNullExceptionTestUpdate() {
		Command command = new Command();
		assertThrows(IdNullExcpetion.class, () -> { commandSrv.update(command); });
	}

	@Test
	void IdNullExceptionTestFindById() {
		Command command = new Command();
		assertThrows(IdNullExcpetion.class, () -> { commandSrv.findById(command.getId()); });
	}
	
	@Test
	void IdNullExceptionTestDeleteById() {
		Command command = new Command();
		assertThrows(IdNullExcpetion.class, () -> { commandSrv.deleteById(command.getId()); });
	}
	
	@Test
	void IdNullExceptionTestFindByIdWithCommandLine() {
		Command command = new Command();
		assertThrows(IdNullExcpetion.class, () -> { commandSrv.findByIdWithCommandLine(command.getId()); });
	}
	
	@Test
	void findAllTest() {
		assertNotNull(commandSrv.findAll());
	}
	
	@Test
	void updateTest() {
		Command command = new Command();
		command = commandSrv.create(command);
		command.setDate(LocalDate.now());
		commandSrv.update(command);
		assertEquals(commandSrv.findById(command.getId()).getDate(), LocalDate.now());
	}

	@Test
	void deleteTest() {
		Command command = new Command();
		command = commandSrv.create(command);
		commandSrv.delete(command);

		assertEquals(0, commandSrv.findAll().size());
	}


}
