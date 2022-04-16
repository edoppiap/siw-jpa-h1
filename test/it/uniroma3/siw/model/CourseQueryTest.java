package it.uniroma3.siw.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseQueryTest {
	
	private static Course c1;
	private static Course c2;
	private static Professor p1;
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("course-unit-test");
		em = emf.createEntityManager();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		c1 = new Course("Fisica", LocalDate.of(2021, 9, 14), 4);
		c2 = new Course("Chimica", LocalDate.of(2021, 9, 14), 4);
		p1 = new Professor("Mario", "Rossi", LocalDate.of(1970, 2, 18), "vatin");

		c1.setProfessor(p1);
		p1.setCourses(Collections.singletonList(c1));
		
		Query deleteAll = em.createQuery("DELETE FROM Course c");
		tx = em.getTransaction();
		tx.begin();
		deleteAll.executeUpdate(); //per sicurezza cancello tutto nella tabella 
		tx.commit();
		
		deleteAll = em.createQuery("DELETE FROM Professor p");
		tx = em.getTransaction();
		tx.begin();
		deleteAll.executeUpdate(); //per sicurezza cancello tutto nella tabella 
		tx.commit();
		
		TypedQuery<Course> cQuery = em.createQuery("SELECT c FROM Course c", Course.class);
		assertEquals("la tabella course non è vuota", 0, cQuery.getResultList().size());
		
		TypedQuery<Professor> pQuery = em.createQuery("SELECT p FROM Professor p", Professor.class);
		assertEquals("la tabella professor non è vuota", 0, pQuery.getResultList().size());
		
		tx.begin();
		em.persist(c1);
		em.persist(c2);
		em.persist(p1);
		tx.commit();
		
		assertEquals("non ci sono 2 tuple course", 2, cQuery.getResultList().size());
		assertEquals("non ci sta una tupla professor", 1, pQuery.getResultList().size());
	}

	@Test
	void dynamicQueryTest() {
		TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c", Course.class);
		List<Course> result = query.getResultList();
		
		assertEquals(2, result.size());
		assertEquals(c1, result.get(0));
		assertEquals(c2, result.get(1));
		
		Query deleteQuery = em.createQuery("DELETE FROM Course c");
		tx.begin();
		assertEquals(2, deleteQuery.executeUpdate());
		tx.commit();
	}
	
	@Test
	void nativQueryTest() {
		Query query = em.createNativeQuery("SELECT * FROM Course", Course.class);
		@SuppressWarnings("unchecked")
		List<Course> result = query.getResultList();
		
		assertEquals(2, result.size());
		assertEquals(c1, result.get(0));
		assertEquals(c2, result.get(1));
		
		Query deleteQuery = em.createNativeQuery("DELETE FROM Course c");
		tx.begin();
		assertEquals(2, deleteQuery.executeUpdate());
		tx.commit();
	}
	
	@Test
	void courseProfessorAssociationTest() {
		TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE c.name='Fisica'", Course.class);
		Course result = query.getSingleResult();
		
		assertEquals(c1, result);
		assertEquals(p1, result.getProfessor());
	}
	
	@Test
	void professorCourseAssociationTest() {
		TypedQuery<Professor> query = em.createQuery("SELECT p FROM Professor p WHERE p.name='Mario'", Professor.class);
		Professor result = query.getSingleResult();
		
		assertEquals(p1, result);
		assertEquals(c1, result.getCourses().get(0));
	}

}
