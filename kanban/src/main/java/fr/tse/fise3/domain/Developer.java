package fr.tse.fise3.domain;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Developer {
	
	@Id
	@GeneratedValue
	private Long id;
	private String firstname;
	private String lastname;
	private String password;
	@Email
	private String email;
	private LocalDate startContract;
	
	@ManyToMany(mappedBy = "developers", fetch = FetchType.EAGER)
	private Set<Task> tasks;
}
