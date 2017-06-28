package be.steformations.it.java_data.contacts;

import java.util.List;
import java.util.Map;

import be.steformations.java_data.contacts.interfaces.jdbc_plus.ContactJdbcManagerPlus;
import be.steformations.java_data.contacts.interfaces.jdbc_plus.JdbcContact;

public class ContactsJdbcImplPlus implements ContactJdbcManagerPlus{

	private String url = "jdbc:postgresql://localhost:5432/contacts";
	private String user = "postgres";
	private String pwd = "postgres";
	
	@Override
	public int countTags() {
		int compteur = 0;
		String sql = "select count(id) from tags";
		
		try(
				java.sql.Connection connexion = java.sql.DriverManager.getConnection(url, user, pwd);
				java.sql.PreparedStatement requete = connexion.prepareStatement(sql);	
				java.sql.ResultSet resultat = requete.executeQuery();
		){

				if (resultat.next()){
					compteur = resultat.getInt(1);
				}
		
		} catch (java.sql.SQLException e){
			e.printStackTrace();
		}		
		System.out.println("compteur: " + compteur);
		return compteur;
	}

	@Override
	public int countContacts() {
		int compteur = 0;
		String sql = "select count(id) from contacts";
		
		try(
				java.sql.Connection connexion = java.sql.DriverManager.getConnection(url, user, pwd);
				java.sql.PreparedStatement requete = connexion.prepareStatement(sql);	
				java.sql.ResultSet resultat = requete.executeQuery();
		){

				if (resultat.next()){
					compteur = resultat.getInt(1);
				}
		
		} catch (java.sql.SQLException e){
			e.printStackTrace();
		}			
		System.out.println("compteur: " + compteur);
		return compteur;
	}

	@Override
	public List<JdbcContact> getAllContacts() {
		List<JdbcContact> listeContacts = new java.util.ArrayList<JdbcContact>();
		String sql = "select nom, prenom, email from contacts";
		
		try(
				java.sql.Connection connexion = java.sql.DriverManager.getConnection(url, user, pwd);
				java.sql.PreparedStatement requete = connexion.prepareStatement(sql);	
				java.sql.ResultSet resultat = requete.executeQuery();
		){

			while (resultat.next()){
	
				String nom = resultat.getString(1);
				String prenom = resultat.getString(2);
				String email = resultat.getString(3);
				JdbcContactImplPlus Jcip = new JdbcContactImplPlus();
				Jcip.setFirstname(prenom);
				Jcip.setName(nom);
				Jcip.setEmail(email);
				System.out.println(prenom + " " + nom + " " + email);
				listeContacts.add(Jcip);
				
				}
		
		} catch (java.sql.SQLException e){
			e.printStackTrace();
		}				
		
		return listeContacts;
	}

	@Override
	public List<JdbcContact> getContactsWithNamedEmail() {
		List<JdbcContact> listeContacts = new java.util.ArrayList<JdbcContact>();
		String sql = "select nom, prenom, email from contacts";
		
		try(
				java.sql.Connection connexion = java.sql.DriverManager.getConnection(url, user, pwd);
				java.sql.PreparedStatement requete = connexion.prepareStatement(sql);	
				java.sql.ResultSet resultat = requete.executeQuery();
		){

			while (resultat.next()){
	
				String nom = resultat.getString(1);
				String prenom = resultat.getString(2);
				String email = resultat.getString(3);
				JdbcContactImplPlus Jcip = new JdbcContactImplPlus();
				Jcip.setFirstname(prenom);
				Jcip.setName(nom);
				Jcip.setEmail(email);				
				if (email.contains(prenom.toLowerCase()) &&
					email.contains(nom.toLowerCase())){	
					System.out.println(prenom + " " + nom + " " + email);
					listeContacts.add(Jcip);
				}
				}
		
		} catch (java.sql.SQLException e){
			e.printStackTrace();
		}				
		
		return listeContacts;
	}

	@Override
	public Map<String, List<JdbcContact>> getContactsByEmailDomains() {

		
	return null;
	}

	@Override
	public Map<String, Map<String, Boolean>> getAllContactsTagsRelationships() {
		
	return null;	
	}

}
