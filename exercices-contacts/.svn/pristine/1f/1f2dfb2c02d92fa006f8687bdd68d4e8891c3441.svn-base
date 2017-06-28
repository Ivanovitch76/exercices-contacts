package be.steformations.java_data.contacts.interfaces.dao;

import be.steformations.java_data.contacts.interfaces.beans.Country;

public interface CountryDao {
	
	/**
	 * Recherche un Country via son abreviation
	 * @param abbreviation l'abréviation du Country
	 * @return
	 */
	Country getCountryByAbbreviation(String abbreviation);
	
	/**
	 * Recherche tous les Country existants
	 * @return java.util.List contenant tous les Country existants
	 */
	java.util.List<? extends Country> getAllCountries();
	
	/**
	 * Insertion d'un nouveau Country
	 * @param abbreviation abréviation du Country
	 * @param name nom du Country
	 * @return nouveau Country ou null si il existe déjà ou si les données sont invalides
	 */
	Country createAndSaveCountry(String abbreviation, String name);
}
