package be.steformations.java_data.contacts.tests.dao.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import be.steformations.java_data.contacts.interfaces.dao.DaoFactory;

public class _00_TestGetDaoFactory {

	private DaoFactory daoFactory = null;
	
	@Before
	public void init() {
		try {
			daoFactory = TestsDaoFactory.getDaoFactory();
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testGetDaoFactory() {
		assertNotNull("TestsDaoFactory.getDaoFactory()", daoFactory);
	}
}
