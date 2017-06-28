package be.steformations.java_data.contacts.interfaces.dao;

import be.steformations.java_data.contacts.interfaces.beans.Tag;


public interface TagDao {
	
	/**
	 * Recherche un Tag via sa valeur
	 * @param value la valeur du Tag
	 * @return le Tag correspondant à la valeur ou null si il n'existe pas
	 */
	Tag getTagByValue(String value);
	
	/**
	 * Recherche un Tag via son identifiant
	 * @param id identifiant du Tag
	 * @return le Tag correspondant à l'identifiant ou null si il n'existe pas
	 */
	Tag getTagById(int id);
	
	/**
	 * Recherche tous les Tags existants
	 * @return java.util.List contenant tous les Tags existants 
	 */
	java.util.List<? extends Tag> getAllTags();
	
	/**
	 * Crée un Tag
	 * @param value la valeur du Tag
	 * @return nouveau Tag ou le Tag ayant cette valeur si il existe déjà
	 */
	Tag createAndSaveTag(String value);
	
	/**
	 * Supprime un Tag
	 * @param id id du Tag à supprimer
	 */
	void removeTag(int id);
}
