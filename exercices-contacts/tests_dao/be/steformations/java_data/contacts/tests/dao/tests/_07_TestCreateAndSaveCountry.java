package be.steformations.java_data.contacts.tests.dao.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.contacts.interfaces.beans.Country;
import be.steformations.java_data.contacts.interfaces.dao.CountryDao;
import be.steformations.java_data.contacts.interfaces.dao.DaoFactory;

public class _07_TestCreateAndSaveCountry {
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
	public void testInsertPaysDejaExistant() {
		try {
			CountryDao dao = daoFactory.getCountryDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getCountryDao()", dao);
		
			Country country = dao.createAndSaveCountry("US", "Etats-Unis");
			assertNull("CountryDao.createAndSaveCountry(\"US\", \"Etats-Unis\")", country);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testInsertPaysAbbreviationNulle() {
		try {
			CountryDao dao = daoFactory.getCountryDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getCountryDao()", dao);
		
			Country country = dao.createAndSaveCountry(null, "Etats-Unis");
			assertNull("CountryDao.createAndSaveCountry(null, \"Etats-Unis\")", country);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}	
		
	@Test
	public void testInsertPaysNomNull() {
		try {
			CountryDao dao = daoFactory.getCountryDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getCountryDao()", dao);
			String abbreviation = "BE-" + System.currentTimeMillis();
			Country country = dao.createAndSaveCountry(abbreviation, null);
			assertNull("CountryDao.createAndSaveCountry(\""+abbreviation+"\", null)", country);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}	
	
	@Test
	public void testInsertPaysInexistant() {
		try {
			CountryDao dao = daoFactory.getCountryDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getCountryDao()", dao);
		
			String abbreviation = "BE-" + System.currentTimeMillis();
			Country country = dao.createAndSaveCountry(abbreviation, "Belgique");
			assertNotNull("CountryDao.createAndSaveCountry(\""+abbreviation+"\", \"Belgique\")", country);
			assertEquals("CountryDao.createAndSaveCountry(\""+abbreviation+"\", \"Belgique\").getAbbreviation()", abbreviation, country.getAbbreviation());
			assertEquals("CountryDao.createAndSaveCountry(\""+abbreviation+"\", \"Belgique\").getName()", "Belgique", country.getName());
			assertTrue("CountryDao.createAndSaveCountry(\""+abbreviation+"\", \"Belgique\").getId() > 0", country.getId() > 0);
			assertEquals("CountryDao.getCountryByAbbreviation(\""+abbreviation+"\", \"Belgique\")", country, dao.getCountryByAbbreviation(abbreviation));
			
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
