package com.skilldistillery.snitchapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {
		private static  EntityManagerFactory emf;
		private static EntityManager em;
		
		private User user;
	
		
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("snitchPU");
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		
		user = em.find(User.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	
		user = null;
	}
	
	@Test
	@DisplayName("not null verified")
	void testSnitchAndUser() {
		assertNotNull(user);
	}
	

	@Test
	@DisplayName("User mapping to fields")
	void testUserEntityMapping() {
		
		assertNotNull(user);
		assertEquals("u@u.com", user.getEmail());
		assertEquals("user", user.getUsername());
		assertEquals(2, user.getId());
	}
	
	

}
