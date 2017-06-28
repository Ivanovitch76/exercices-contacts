package be.steformations.java_data.contacts.tests.dao.tests;


import org.junit.Test;

import be.steformations.java_data.contacts.interfaces.beans.Country;
import be.steformations.java_data.contacts.interfaces.dao.CountryDao;
import be.steformations.java_data.contacts.interfaces.dao.DaoFactory;
import static org.junit.Assert.*;

import org.junit.Before;


public class _05_TestGetCountryByAbbreviation {

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
	public void selectPaysExistant() {
		try {
			CountryDao dao = daoFactory.getCountryDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getCountryDao()", dao);
		
			Country country = dao.getCountryByAbbreviation("US");	
			assertNotNull("CountryDao.getCountryByAbbreviation(\"US\")", country);
			assertEquals("CountryDao.getCountryByAbbreviation(\"US\").getAbbreviation()", "US", country.getAbbreviation());

		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void selectPaysNull() {
		try {
			CountryDao dao = daoFactory.getCountryDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getCountryDao()", dao);
		
			Country country = dao.getCountryByAbbreviation(null);	
			assertNull("CountryDao.getCountryByAbbreviation(null)", country);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void selectPaysInexistant() {
		try {
			CountryDao dao = daoFactory.getCountryDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getCountryDao()", dao);
		
			Country country = dao.getCountryByAbbreviation("USA");	
			assertNull("CountryDao.getCountryByAbbreviation(\"USA\")", country);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}	
}
