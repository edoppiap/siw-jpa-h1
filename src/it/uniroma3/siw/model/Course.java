package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Course {
	
	public Course() {}
	
	public Course(String name, LocalDate startDate, int monthsDuration) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.monthsDuration = monthsDuration;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	private LocalDate startDate;
	
	private int monthsDuration;
	
	/*
	 * Fetch di default: LAZY 
	 * Va bene perche' quando chiediamo i corsi, non e' detto che vogliamo
	 * conoscere anche tutti gli studenti che lo seguono
	 */
	@ManyToMany(mappedBy = "courses")
	private List<Student> students;		//Lista di studenti frequentanti il corso
	
	/*
	 * Fetch di default: EAGER
	 * Va bene perche' quando chiediamo i corsi ci interessa sapere anche
	 * il docente che li tengono
	 */
	@ManyToOne
	private Professor professor;		//Professore che tiene il corso

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getMonthsDuration() {
		return monthsDuration;
	}

	public void setMonthsDuration(int monthsDuration) {
		this.monthsDuration = monthsDuration;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + monthsDuration;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		Course other = (Course) obj;
		if (monthsDuration != other.monthsDuration)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Course");
		sb.append("{id=").append(id);
		sb.append(", name=").append(name);
		sb.append(", startDate=").append(startDate.toString());
		sb.append(", monthsDuration=").append(monthsDuration);
		sb.append("}\n");
		
		return sb.toString();
	}

}
