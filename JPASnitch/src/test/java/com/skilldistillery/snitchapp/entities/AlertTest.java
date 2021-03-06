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

class AlertTest {
	private static  EntityManagerFactory emf;
	private static EntityManager em;

	private Alert alert;
	
	
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
		alert = em.find(Alert.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		alert = null;
	}
	
	@Test
	@DisplayName("Snitch test verify not null")
	void testSnitchAndUserNotNull() {
		assertNotNull(alert);
		
	}
	
	
	@Test
	@DisplayName("mapping Alert content")
	void testAlert() {
		assertNotNull(alert);
		assertEquals("Construction on I-25", alert.getSubject() );
		assertEquals("Slow moving traffic between Monument and Castle rock causing delays in commute.", alert.getContent() );
	}
	
	@Test
	@DisplayName("mapping Alert content img and zipcode entry")
	void testAlertImgAndZip() {
		assertNotNull(alert);
		assertEquals("https://images.unsplash.com/photo-1415594445260-63e18261587e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80", alert.getImgUrl() );
		assertEquals(80014, alert.getZipcode() );
	}
	
	
	@Test
	@DisplayName("mapping Alert to Category")
	void testAlertToCategory() {
		assertNotNull(alert);
		assertNotNull(alert.getCategory());
		assertEquals("Traffic", alert.getCategory().getName());
	}
	
	@Test
	@DisplayName("mapping Alert to User")
	void testAlertToUser() {
		assertNotNull(alert);
		assertNotNull(alert.getUser());
		assertEquals("admin", alert.getUser().getUsername());
	}
	

}
