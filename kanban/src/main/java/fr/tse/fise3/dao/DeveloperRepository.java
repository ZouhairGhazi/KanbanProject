package fr.tse.fise3.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.tse.fise3.domain.Developer;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {

}
