package fr.tse.fise3.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeLog {
	
	@Id
	@GeneratedValue
	private Long id;
	private Date occured;
	
	@ManyToOne
	private Task task;
	
	@ManyToOne
	private TaskStatus targetStatus;
	
	@ManyToOne
	private TaskStatus sourceStatus;
}
