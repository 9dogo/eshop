package project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.entities.Template;
import project.exceptions.TemplateException;
import project.exceptions.CheckId;
import project.repositories.TemplateRepository;

@Service
//traitement disponible sur les templates
public class TemplateService {

	@Autowired
	private TemplateRepository templateRepository;

	public Template create(Template template) {
		CheckId.checkIdNull(template.getId());
		return templateRepository.save(template);
	}

	public Template findById(Long id) {
		CheckId.checkIdNotNull(id);
		return templateRepository.findById(id).orElseThrow(() -> {throw new TemplateException("unable to find the Template with id "+id);});
	}

	public List<Template> findAll() {
		return templateRepository.findAll();
	}

	public Template update(Template template) {
		CheckId.checkIdNotNull(template.getId());
		return templateRepository.save(template);
	}

	public void deleteById(Long id) {
		CheckId.checkIdNotNull(id);
		templateRepository.delete(findById(id));
	}

	public void delete(Template template) {
		CheckId.checkIdNotNull(template.getId());
		deleteById(template.getId());
	}
}