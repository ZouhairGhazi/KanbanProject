package fr.tse.fise3.utils;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import fr.tse.fise3.dao.DeveloperRepository;
import fr.tse.fise3.dao.TaskRepository;
import fr.tse.fise3.dao.TaskStatusRepository;
import fr.tse.fise3.dao.TaskTypeRepository;
import fr.tse.fise3.domain.Developer;
import fr.tse.fise3.domain.Task;
import fr.tse.fise3.domain.TaskStatus;
import fr.tse.fise3.domain.TaskType;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@Component
public class LoadDatabase {

	@Bean
	CommandLineRunner initTestDatabase(TaskStatusRepository taskStatusRepository,
			DeveloperRepository developerRepository, TaskTypeRepository taskTypeRepository,
			TaskRepository taskRepository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {

				initTaskStatus(taskStatusRepository);
				initTaskTypes(taskTypeRepository);
				Developer dev = initDevelopers(developerRepository);
				Task task1 = initTasks(taskRepository, dev, taskStatusRepository, taskTypeRepository,"Fix memory leaks");
				Task task2 = initTasks(taskRepository, dev, taskStatusRepository, taskTypeRepository,"Hotfix LTS version");
			}
		};
	}

	private void initTaskStatus(TaskStatusRepository taskStatusRepository) {
		TaskStatus todoTask = new TaskStatus(Constants.TASK_STATUS_ID_TODO, Constants.TASK_STATUS_LABEL_TODO);
		taskStatusRepository.save(todoTask);
		log.info("New item added to Database " + todoTask);

		TaskStatus doingTask = new TaskStatus(Constants.TASK_STATUS_ID_DOING, Constants.TASK_STATUS_LABEL_DOING);
		taskStatusRepository.save(doingTask);
		log.info("New item added to Database " + doingTask);

		TaskStatus testTask = new TaskStatus(Constants.TASK_STATUS_ID_TEST, Constants.TASK_STATUS_LABEL_TEST);
		taskStatusRepository.save(testTask);
		log.info("New item added to Database " + testTask);

		TaskStatus doneTask = new TaskStatus(Constants.TASK_STATUS_ID_DONE, Constants.TASK_STATUS_LABEL_DONE);
		taskStatusRepository.save(doneTask);
		log.info("New item added to Database " + doneTask);

	}

	private void initTaskTypes(TaskTypeRepository taskTypeRepository) {
		TaskType bugTask = new TaskType(Constants.TASK_TYPES_ID_BUG, Constants.TASK_TYPES_LABEL_BUG);
		taskTypeRepository.save(bugTask);
		log.info("New item added to Database " + bugTask);

		TaskType featureTask = new TaskType(Constants.TASK_TYPES_ID_FEATURE, Constants.TASK_TYPES_LABEL_FEATURE);
		taskTypeRepository.save(featureTask);
		log.info("New item added to Database " + featureTask);
	}

	private Developer initDevelopers(DeveloperRepository developerRepository) {

		Developer dev = new Developer();
		dev.setFirstname("Zouhair");
		dev.setEmail("zouhair.ghazi@telecom-st-etienne.fr");
		dev.setLastname("Ghazi");
		dev.setPassword("zou123");
		dev.setStartContract(LocalDate.now());
		Developer holder_dev = developerRepository.save(dev);
		log.info("New item added to Database " + dev);
		return holder_dev;
	}

	private Task initTasks(TaskRepository taskRepository, Developer dev, TaskStatusRepository taskStatusRepository,
			TaskTypeRepository taskTypeRepository,String title) {
		Task taskOne = new Task();
		taskOne.addDeveloper(dev);
		taskOne.setTitle(title);
		taskOne.setCreateDate(LocalDate.now());
		taskOne.setNbHoursForecast(0);
		taskOne.setNbHoursReal(0);
		taskOne.setStatus(taskStatusRepository.findById(Constants.TASK_STATUS_ID_TODO).orElse(null));
		taskOne.setType(taskTypeRepository.findById(Constants.TASK_TYPES_ID_BUG).orElse(null));
		Task task = taskRepository.save(taskOne);
		log.info("New Task added to Database" + taskOne);
		return task;
	}
}