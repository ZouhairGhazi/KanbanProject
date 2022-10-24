package fr.tse.fise3.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.tse.fise3.domain.TaskType;

public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {
	
}
