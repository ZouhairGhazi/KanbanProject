package fr.tse.fise3.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.tse.fise3.domain.TaskStatus;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {

}
