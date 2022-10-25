package fr.tse.fise3.services.impl;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.tse.fise3.dao.TaskRepository;
import fr.tse.fise3.dao.TaskStatusRepository;
import fr.tse.fise3.dao.TaskTypeRepository;
import fr.tse.fise3.domain.ChangeLog;
import fr.tse.fise3.domain.Task;
import fr.tse.fise3.domain.TaskStatus;
import fr.tse.fise3.domain.TaskType;
import fr.tse.fise3.services.TaskService;
import fr.tse.fise3.utils.Constants;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	private final TaskStatusRepository taskStatusRepository;
	private final TaskTypeRepository taskTypeRepository;

	@Override
	@Transactional(readOnly = true)
	public Collection<Task> findAllTasks() {
		// TODO Auto-generated method stub
		return this.taskRepository.findAll();
	}
 
	@Override
	@Transactional(readOnly = true)
	public Collection<TaskType> findAllTaskTypes() {
		// TODO Auto-generated method stub
		return this.taskTypeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<TaskStatus> findAllTaskStatus() {
		// TODO Auto-generated method stub
		return this.taskStatusRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Task findTask(Long id) {
		// TODO Auto-generated method stub
		return this.taskRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public TaskType findTaskType(Long id) {
		// TODO Auto-generated method stub
		return this.taskTypeRepository.findById(id).get();
	}

	@Override
	@Transactional(readOnly = true)
	public TaskStatus findTaskStatus(Long id) {
		// TODO Auto-generated method stub
		return this.taskStatusRepository.findById(id).get();
	}

	@Override
	public Task createTask(Task task) {
		// TODO Auto-generated method stub
		Task newTask = taskRepository.save(task);
		return newTask;
	}

	@Override
	public Task deleteTask(Task task) {
		// TODO Auto-generated method stub
		Task existingTask = taskRepository.findById(task.getId()).get();
		if (existingTask != null)
			taskRepository.delete(existingTask);
		return task;
	}

	@Override
	@Transactional(readOnly = true)
	public TaskStatus getFollowingTask(TaskStatus currentTaskStatus) {
		// TODO Auto-generated method stub
		TaskStatus followingTask = null;

		TaskStatus todo = this.findTaskStatus(Constants.TASK_STATUS_ID_TODO);
		TaskStatus doing = this.findTaskStatus(Constants.TASK_STATUS_ID_DOING);
		TaskStatus test = this.findTaskStatus(Constants.TASK_STATUS_ID_TEST);
		TaskStatus done = this.findTaskStatus(Constants.TASK_STATUS_ID_DONE);

		if (currentTaskStatus.equals(todo)) {
			followingTask = doing;
		} else if (currentTaskStatus.equals(doing)) {
			followingTask = test;
		} else if (currentTaskStatus.equals(test)) {
			followingTask = done;
		} else if (currentTaskStatus.equals(done)) {
			// no more taskStatus to follow the treatment will be if (getFollowingTask(task)
			// == null) means that there's no
			// more taskStatus left
			followingTask = null;
		}
		return followingTask;
	}

	@Override
	@Transactional(readOnly = true)
	public TaskStatus getPreviousTask(TaskStatus currentTaskStatus) {
		// TODO Auto-generated method stub
		TaskStatus previousTask = null;

		TaskStatus todo = this.findTaskStatus(Constants.TASK_STATUS_ID_TODO);
		TaskStatus doing = this.findTaskStatus(Constants.TASK_STATUS_ID_DOING);
		TaskStatus test = this.findTaskStatus(Constants.TASK_STATUS_ID_TEST);
		TaskStatus done = this.findTaskStatus(Constants.TASK_STATUS_ID_DONE);

		if (currentTaskStatus.equals(todo)) {
			previousTask = null;
		} else if (currentTaskStatus.equals(doing)) {
			previousTask = todo;
		} else if (currentTaskStatus.equals(test)) {
			previousTask = doing;
		} else if (currentTaskStatus.equals(done)) {
			previousTask = test;
		}
		return previousTask;
	}

	@Override
	@Transactional(readOnly = true)
	public Task updateTaskStatus(Task task, TaskStatus nextTaskStatus) {
		// TODO Auto-generated method stub
		task = this.taskRepository.save(task);
		ChangeLog changeLog = new ChangeLog();
		//changeLog.setOccured(LocalDateTime.now());
		changeLog.setSourceStatus(task.getStatus());
		changeLog.setTargetStatus(nextTaskStatus);
		task.setStatus(nextTaskStatus);
		//task.addChangeLog(changeLog);
		return task;
	}

	@Override
	@Transactional(readOnly = true)
	public Task moveRightTask(Task task) {
		// TODO Auto-generated method stub
		TaskStatus nextTaskStatus = this.getFollowingTask(task.getStatus());
		Task newTask = this.updateTaskStatus(task, nextTaskStatus);
		return newTask;
	}

	@Override
	@Transactional(readOnly = true)
	public Task moveLeftTask(Task task) {
		// TODO Auto-generated method stub
		TaskStatus previousTaskStatus = this.getPreviousTask(task.getStatus());
		Task newTask = this.updateTaskStatus(task, previousTaskStatus);
		return newTask;
	}

}