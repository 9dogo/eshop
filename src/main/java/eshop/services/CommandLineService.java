package eshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.model.CommandLine;
import project.exceptions.CommandLineException;
import project.exceptions.CheckId;
import project.repositories.CommandLineRepository;

@Service
//traitement disponible sur les commandlines
public class CommandLineService {

	@Autowired
	private CommandLineRepository commandlineRepository;

	public CommandLine create(CommandLine commandline) {
		CheckId.checkIdNull(commandline.getId());
		return commandlineRepository.save(commandline);
	}

	public CommandLine findById(Long id) {
		CheckId.checkIdNotNull(id);
		return commandlineRepository.findById(id).orElseThrow(() -> {throw new CommandLineException("unable to find the CommandLine with id "+id);});
	}

	public List<CommandLine> findAll() {
		return commandlineRepository.findAll();
	}

	public CommandLine update(CommandLine commandline) {
		CheckId.checkIdNotNull(commandline.getId());
		return commandlineRepository.save(commandline);
	}

	public void deleteById(Long id) {
		CheckId.checkIdNotNull(id);
		commandlineRepository.delete(findById(id));
	}

	public void delete(CommandLine commandline) {
		CheckId.checkIdNotNull(commandline.getId());
		deleteById(commandline.getId());
	}
}