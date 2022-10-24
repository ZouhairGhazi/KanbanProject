package fr.tse.fise3.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.tse.fise3.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
