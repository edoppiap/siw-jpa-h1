package it.uniroma3.siw.main;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import it.uniroma3.siw.model.Address;
import it.uniroma3.siw.model.Company;
import it.uniroma3.siw.model.Course;
import it.uniroma3.siw.model.Professor;
import it.uniroma3.siw.model.Student;

public class CourseMain {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("course-unit");
		EntityManager em = emf.createEntityManager();
		
		//inizializzo istanze
		Course course1 = new Course("Fisica", LocalDate.of(2021, 9, 14), 5);
		Course course2 = new Course("Chimica", LocalDate.of(2021, 9, 14), 5);
		Course course3 = new Course("Analisi", LocalDate.of(2021, 9, 14), 5);
		Professor professor1 = new Professor("Mario", "Rossi", LocalDate.of(2070, 9, 1), "vatin");
		Professor professor2 = new Professor("Luigi", "Bianchi", LocalDate.of(2059, 6, 1), "vatin");
		Student student1 = new Student("Carlo", "Conti", "conti@mail", LocalDate.of(2097, 6, 1), "Roma");
		Student student2 = new Student("Bruno", "Salvi", "salvi@mail", LocalDate.of(2096, 5, 2), "Roma");
		Student student3 = new Student("Michele", "Micheli", "micheli@mail", LocalDate.of(2095, 8, 15), "Roma");
		Company company1 = new Company("Tech1", "333333333");
		Company company2 = new Company("Tech2", "333333332");
		
		//implemento associazioni
		student1.setCourses(Arrays.asList(course1, course3));
		student2.setCourses(Arrays.asList(course1, course2, course3));
		student3.setCourses(Collections.singletonList(course2));
		course1.setStudents(Arrays.asList(student1, student2));
		course2.setStudents(Arrays.asList(student2, student3));
		course3.setStudents(Arrays.asList(student1, student2));
		
		professor1.setCourses(Arrays.asList(course1, course3));
		professor2.setCourses(Collections.singletonList(course2));
		course1.setProfessor(professor1);
		course2.setProfessor(professor2);
		course3.setProfessor(professor1);
		
		company1.setAddress(new Address("Rossi", "72", 00456, "Roma", "RM"));
		company2.setAddress(new Address("Cina", "115a", 00457, "Roma", "RM"));
		
		student1.setCompany(company1);
		student2.setCompany(company2);
		student3.setCompany(company2);
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(student1);
		em.persist(student2);
		em.persist(student3);
		em.persist(professor1);
		em.persist(professor2);
		em.persist(course1);
		em.persist(course2);
		em.persist(course3);
		tx.commit();
		
		Map<Course, List<Student>> course2students = new HashMap<>();
		TypedQuery<Course> courseQuery = em.createQuery("SELECT c FROM Course c", Course.class);
		
		for(Course course: courseQuery.getResultList()) {
			course2students.put(course, course.getStudents());
		}
		
		System.out.print(course2students.toString());
		
		Map<Student, List<Course>> student2courses = new HashMap<>();
		TypedQuery<Student> studentQuery = em.createQuery("SELECT s FROM Student s", Student.class);
		
		for(Student student :studentQuery.getResultList()) {
			student2courses.put(student, student.getCourses());
		}
		
		System.out.print(student2courses.toString());		
		
		em.close();
		emf.close();
	}

}
