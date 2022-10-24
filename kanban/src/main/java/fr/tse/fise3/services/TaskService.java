package fr.tse.fise3.services;

import java.util.Collection;
import fr.tse.fise3.domain.Task;
import fr.tse.fise3.domain.TaskStatus;
import fr.tse.fise3.domain.TaskType;

public interface TaskService {
	
	Collection<Task> findAllTasks();
	
	Task findTask(Long id);

	Task moveRightTask(Task task);
	
	Task moveLeftTask(Task task);
	
	// Additional methods
	
	Collection<TaskType> findAllTaskTypes();

	Collection<TaskStatus> findAllTaskStatus();

	TaskType findTaskType(Long id);

	TaskStatus findTaskStatus(Long id);	
	
	Task updateTaskStatus(Task task, TaskStatus nextTaskStatus);

	TaskStatus getFollowingTask(TaskStatus currentTaskStatus);
	
	TaskStatus getPreviousTask(TaskStatus currentTaskStatus);
	
	Task createTask(Task task);
	
	Task deleteTask(Task task);
}