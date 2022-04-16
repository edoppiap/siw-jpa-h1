package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Professor {
	
	public Professor() {
		
	}
	
	public Professor(String name, String lastName, LocalDate birthDate, String vatin) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.vatin = vatin;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String lastName;
	
	private LocalDate birthDate;
	
	@Column(nullable = false)
	private String vatin;
	
	@OneToMany(mappedBy = "professor")
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

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getVatin() {
		return vatin;
	}

	public void setVatin(String vatin) {
		this.vatin = vatin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vatin == null) ? 0 : vatin.hashCode());
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
		Professor other = (Professor) obj;
		if (vatin == null) {
			if (other.vatin != null)
				return false;
		} else if (!vatin.equals(other.vatin))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		
		sb.append("Professor");
		sb.append("{id=").append(id);
		sb.append(", name=").append(name);
		sb.append(", lastName=").append(lastName);
		sb.append(", birthDate=").append(birthDate);
		sb.append(", vatin=").append(vatin);
		sb.append("}\n");
		
		return sb.toString();
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

}
