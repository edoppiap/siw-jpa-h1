package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Student {
	
	public Student() {}
	
	public Student(String name, String lastName, String email, LocalDate birthDate, String birthCity) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
		this.birthCity = birthCity;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(unique=true, nullable = false)
	private String email;
	
	private LocalDate birthDate;
	
	private String birthCity;
	
	/* 
	 * Quando inseriamo un nuovo studente è utile avere un evento a cascata per 
	 * l'inserimento anche dell'azienda per cui lavora. Non per quando lo cancelliamo 
	 * perché per la stessa azienda potrebbero lavorare anche altri studenti
	 */
	@ManyToOne(cascade = {CascadeType.PERSIST})
	private Company company;
	
	/*
	 * Quando vogliamo vedere le info sui corsi che uno studente segue probabilmente 
	 * vorremo anche sapere il docente che tiene ciascun corso
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Course> courses;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String surname) {
		this.lastName = surname;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthCity() {
		return birthCity;
	}

	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthCity == null) ? 0 : birthCity.hashCode());
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (birthCity == null) {
			if (other.birthCity != null)
				return false;
		} else if (!birthCity.equals(other.birthCity))
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Student");
		sb.append("{id=").append(id);
		sb.append(", firstName=").append(name);
		sb.append(", lastName=").append(lastName);
		sb.append(", email=").append(email);
		sb.append(", birthDate=").append(birthDate.toString());
		sb.append(", birthCity=").append(birthCity);
		
		return sb.toString();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
