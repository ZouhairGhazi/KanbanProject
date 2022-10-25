package fr.tse.fise3.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.tse.fise3.dao.DeveloperRepository;
import fr.tse.fise3.domain.Developer;
import fr.tse.fise3.services.DeveloperService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {

	private final DeveloperRepository developerRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Developer> findAllDevelopers() {
		// TODO Auto-generated method stub
		return this.developerRepository.findAll();
	}

	/*@Override
	public Optional<Developer> findDeveloperById(Long id) {
		// TODO Auto-generated method stub
		return this.developerRepository.findById(id);
	}*/

}