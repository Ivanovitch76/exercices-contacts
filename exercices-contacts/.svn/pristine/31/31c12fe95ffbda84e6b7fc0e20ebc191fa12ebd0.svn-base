package be.steformations.java_data.contacts.interfaces.dao;


import be.steformations.java_data.contacts.interfaces.beans.Contact;
import be.steformations.java_data.contacts.interfaces.beans.Tag;


public interface ContactDao {
	
	/**
	 * Recherche un Contact via son nom et son prénom
	 * @param firstname le prénom du Contact
	 * @param name le nom du Contact
	 * @return le Contact correspondant ou null si il n'existe pas
	 */
	Contact getContactByFirstnameAndName(String firstname, String name);
	
	/**
	 * Recherche un Contact via son identifiant
	 * @param id l'identifiant du Contact
	 * @return le Contact correspondant à l'identifiant ou null si il n'existe pas
	 */
	Contact getContactById(int id);
	
	/**
	 * Recherche les Contact liés à un Country déterminé
	 * @param abbreviation du Country source de la recherche
	 * @return la liste des Contact liés à ce Country
	 */
	java.util.List<? extends Contact> getContactsByCountry(String abbreviation);
	
	/**
	 * Recherche tous les Contact existants
	 * @return java.util.List contenant tous les Contact existants
	 */
	java.util.List<? extends Contact> getAllContacts();
	
	/**
	 * Recherche les Tag associés à un Contact
	 * @param id id du Contact source de la recherche 
	 * @return la liste des Tag associés à ce Contact
	 */
	java.util.List<? extends Tag> getTagsByContact(int id);
	
	/**
	 * Insére un nouveau Contact
	 * @param firstname le prénom du Contact
	 * @param name le nom du Contact
	 * @param email l'email du Contact
	 * @param countryAbbreviation l'abreviation du Country du Contact
	 * @param tagValues les valeurs des Tag associés au Contact
	 * @return nouveau Contact ou null si les données sont invalides ou dupliquées
	 */
	Contact createAndSaveContact(String firstname, String name, String email, String countryAbbreviation, java.util.List<String> tagValues);
	
	/**
	 * Supprime un Contact
	 * @param id id du Contact à supprimer
	 * @return true si la suppression a été effectuée - false si le Contact n'existait pas
	 */
	boolean removeContact(int id);
	
}
