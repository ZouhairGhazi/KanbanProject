package fr.tse.fise3.domain;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
	
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private Integer nbHoursForecast;
	private Integer nbHoursReal;
	private LocalDate createDate;
	
	@ManyToOne
	private TaskStatus status;
	
	@ManyToOne
	private TaskType type;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Developer> developers;

	@OneToMany(mappedBy = "task", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private Set<ChangeLog> changeLogs;

	public void addDeveloper(Developer dev) {
		dev.getTasks().add(this);
		this.developers.add(dev);
	}
}
