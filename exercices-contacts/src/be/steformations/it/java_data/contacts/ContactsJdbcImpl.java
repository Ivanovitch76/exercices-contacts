package be.steformations.it.java_data.contacts;

import java.util.List;

import be.steformations.java_data.contacts.interfaces.jdbc.ContactJdbcManager;

public class ContactsJdbcImpl implements ContactJdbcManager {

	private String url = "jdbc:postgresql://localhost:5432/contacts";
	private String user = "postgres";
	private String pwd = "postgres";
	
	@Override
	/**
	 * Recherche de l' email du contact sans tenir compte de la casse des arguments
	 * @param firstname prénom du contact
	 * @param name nom du contact
	 * @return l'email du contact ou null si le contact n'a pas d'email  
	 */
	public String getEmailByContact(String firstname, String name) {
		String email = null;
		
		if (firstname != null && name != null){
			String sql = "select email from contacts where lower(prenom) = lower(?) "
					+ "and lower(nom) = lower(?)";
			
			try(
					java.sql.Connection connexion = java.sql.DriverManager.getConnection(url, user, pwd);
					java.sql.PreparedStatement requete = connexion.prepareStatement(sql)						
			){
				requete.setString(1, firstname);
				requete.setString(2, name);
				
				try ( 
						java.sql.ResultSet resultat = requete.executeQuery();
				){
					if (resultat.next()){
						email = resultat.getString(1);
					}
				} catch (java.sql.SQLException e){
					e.printStackTrace();
				}	
			
			} catch (java.sql.SQLException e){
				e.printStackTrace();
			}				
		}
		return email;
	}

	@Override
	/**
	 * Recherche des emails des contacts habitant dans un pays déterminé
	 * @param abbreviation abreviation de pays
	 * @return liste des emails des contacts de ce pays ou une liste vide si le pays n'existe pas ou si il n'y a pas de contacts dans ce pays
	 */
	public List<String> getEmailsByCountry(String abbreviation) {
		List<String> emails = new java.util.ArrayList<String>();
		
		if (abbreviation != null){
			String sql = "select email from contacts c join pays p on c.pays = p.id "
					+ "where p.abreviation = ?";
			
			try(
					java.sql.Connection connexion = java.sql.DriverManager.getConnection(url, user, pwd);
					java.sql.PreparedStatement requete = connexion.prepareStatement(sql)						
			){
				requete.setString(1, abbreviation);
				
				try ( 
						java.sql.ResultSet resultat = requete.executeQuery();
				){
					while (resultat.next()){
						String email = resultat.getString(1);
						emails.add(email);
					}
				} catch (java.sql.SQLException e){
					e.printStackTrace();
				}	
			
			} catch (java.sql.SQLException e){
				e.printStackTrace();
			}				
		}
		return emails;
	}

	@Override
	/**
	 * Recherche les tags associés à un contact
	 * @param firstname prénom du contact
	 * @param name nom du contact
	 * @return la liste des tags associés au contact ou une liste vide si le contact n'existe pas ou si il n'a pas de tags
	 */
	public List<String> getTagsByContact(String firstname, String name) {
		List<String> tags = new java.util.ArrayList<String>();
		
		if (firstname != null && name != null){
			String sql = "select t.tag from tags t, contacts c, contacts_tags ct "
					+ "where c.id = ct.contact and ct.tag = t.id "
					+ "and nom = ? and prenom = ?";
			
			try(
					java.sql.Connection connexion = java.sql.DriverManager.getConnection(url, user, pwd);
					java.sql.PreparedStatement requete = connexion.prepareStatement(sql)						
			){
				requete.setString(1, name);
				requete.setString(2, firstname);
				
				try ( 
						java.sql.ResultSet resultat = requete.executeQuery();
				){
					while (resultat.next()){
						String tag = resultat.getString(1);
						tags.add(tag);
					}
				}	
			
			} catch (java.sql.SQLException e){
				e.printStackTrace();
			}				
		}
		return tags;
	}

	@Override
	/**
	 * Ajout d'un contact et des nouveaux tags qui lui sont associés
	 * @param firstname prénom du contact
	 * @param name nom du contact
	 * @param email email du contact
	 * @param countryAbbreviation abréviation du pays du contact (éventuellement null) 
	 * @param tagsValues valeurs des tags à associer au contact (éventuellement nouveaux)
	 * @return l'identifiant du contact (lorsque le contact a été ajouté) ou 0 en cas de duplication ou de données incorrectes
	 */
	public int createAndSaveContact(String firstname, String name, String email, String countryAbbreviation,
			String[] tagsValues) {
		int idContact = 0;
		int codeAbr = 0;
		int idTag = 0;
		boolean cdpays = false;
		boolean doublon = false;
		
		if (firstname != null && name != null && email != null){
			String sqlSelAb = "select id from pays where abreviation = ?";
			String sqlInsertContacts = "insert into contacts(prenom, nom, email, pays) "
					+ "values(?, ?, ?, ?)";
			String sqlSelectId = "select id from contacts where prenom = ? and nom = ?";
			String sqlSelectTags = "select id from tags where tag = ?";
			String sqlInsertTags = "insert into tags(tag) values(?)";
			String sqlInsertContactsTags = "insert into contacts_tags(contact, tag) values (?, ?)";
			
			try(
					java.sql.Connection connexion = java.sql.DriverManager.getConnection(url, user, pwd);					
			){
				connexion.setAutoCommit(false);
				if (countryAbbreviation != null){
					try (java.sql.PreparedStatement requete = connexion.prepareStatement(sqlSelAb);) {
						requete.setString(1, countryAbbreviation);

						try (java.sql.ResultSet resultat = requete.executeQuery();) {
							if (resultat.next()) {
								codeAbr = resultat.getInt("id");
								cdpays = true;
							}						
						}
					}
				} else {
					cdpays = true;
				}
				try(
						java.sql.PreparedStatement requete = connexion.prepareStatement(sqlSelectId);						
					){
					requete.setString(1, firstname);
					requete.setString(2, name);
					
					try ( 
							java.sql.ResultSet resultat = requete.executeQuery();		
					){
						if(resultat.next()){
							doublon = true;
						}
					} catch (java.sql.SQLException e){
						e.printStackTrace();
					}	
					
				}
				if (cdpays && doublon == false){
				try(
						java.sql.PreparedStatement requete = connexion.prepareStatement(sqlInsertContacts);	
					){
						requete.setString(1, firstname);
						requete.setString(2, name);
						requete.setString(3, email);
						if (codeAbr != 0){
							requete.setInt(4, codeAbr);
						} else {
							requete.setNull(4, java.sql.Types.INTEGER);
						}
						
						// insert, update, delete
						requete.executeUpdate();
						connexion.commit(); //modifications sont définitivement enregistrées
					}	
				try(
						java.sql.PreparedStatement requete = connexion.prepareStatement(sqlSelectId);						
					){
					requete.setString(1, firstname);
					requete.setString(2, name);
					
					try ( 
							java.sql.ResultSet resultat = requete.executeQuery();		
					){
						if(resultat.next()){
							idContact = resultat.getInt("id");
						}
					} catch (java.sql.SQLException e){
						e.printStackTrace();
					}	
					
				}
				if (tagsValues != null){
					for (String s : tagsValues){
						try(
								java.sql.PreparedStatement requete = connexion.prepareStatement(sqlSelectTags);	
							){
							requete.setString(1, s);	
							
							try ( 
									java.sql.ResultSet resultat = requete.executeQuery();		
							){
								if(resultat.next()){
									idTag = resultat.getInt("id");
									try(
											java.sql.PreparedStatement requete2 = connexion.prepareStatement(sqlInsertContactsTags);		
									){
										requete2.setInt(1, idContact);
										requete2.setInt(2, idTag);
										
										requete2.executeUpdate();
										connexion.commit(); //modifications sont définitivement enregistrées
									}
								} else {
									try(
											java.sql.PreparedStatement requete2 = connexion.prepareStatement(sqlInsertTags);		
									){											
											requete2.setString(1, s);
										
											requete2.executeUpdate();
											connexion.commit();
									}
									try(
											java.sql.PreparedStatement requete2 = connexion.prepareStatement(sqlSelectTags)						
									){
										requete2.setString(1, s);
										
										try ( 												
												java.sql.ResultSet resultat2 = requete2.executeQuery();
										){
											if (resultat2.next()){
												idTag = resultat2.getInt("id");
												System.out.println("resultat2.next= " + resultat2.next() + "idTag= " + idTag);
											}
										} catch (java.sql.SQLException e){
											e.printStackTrace();
										}	
									
									} catch (java.sql.SQLException e){
										e.printStackTrace();
									}				

									System.out.println("before insert contacts tags 2"); 
									System.out.println("resultat.next= " + resultat.next());

										System.out.println("idTag= " + idTag);
										try(
												java.sql.PreparedStatement requete2 = connexion.prepareStatement(sqlInsertContactsTags);		
										){
											requete2.setInt(1, idContact);
											requete2.setInt(2, idTag);
											
											System.out.println("insert contacts tags 2");
											requete2.executeUpdate();
											connexion.commit(); //modifications sont définitivement enregistrées
											System.out.println("after insert contacts tags 2");
										}

								}
							} catch (java.sql.SQLException e){
								e.printStackTrace();
							}	
						}
					}
				}
				}
			} catch (java.sql.SQLException e){
				e.printStackTrace();
			}	
		}
	
		return idContact;
	}

	@Override
	/**
	 * Suppression d'un contact
	 * @param id identifiant du contact
	 */
	public void removeContact(int id) {
		// TODO Auto-generated method stub
		boolean retour = false;
		String sqlSelect = "select * from contacts where id = ?";
		String sqlDeleteContacts = "delete from contacts where id = ?";
		String sqlDeleteContactsTags = "delete from contacts_tags where contact = ?";
		try(
				java.sql.Connection connexion = java.sql.DriverManager.getConnection(url, user, pwd);	
				java.sql.PreparedStatement requete = connexion.prepareStatement(sqlSelect);	
		){
			connexion.setAutoCommit(false);
			requete.setInt(1, id);
			
			try ( 
					java.sql.ResultSet resultat = requete.executeQuery();		
			){
				if(resultat.next()){
					retour = true;
				}			
			} catch (java.sql.SQLException e){
				e.printStackTrace();
			}
			if (retour){
				try(
						java.sql.PreparedStatement requete2 = connexion.prepareStatement(sqlDeleteContactsTags);	
					){
						requete2.setInt(1, id);
						
						// insert, update, delete
						requete2.executeUpdate();
						connexion.commit(); //modifications sont définitivement enregistrées
					} catch (java.sql.SQLException e){
						e.printStackTrace();
					}	
				try(
						java.sql.PreparedStatement requete2 = connexion.prepareStatement(sqlDeleteContacts);	
					){
						requete2.setInt(1, id);
						
						// insert, update, delete
						requete2.executeUpdate();
						connexion.commit(); //modifications sont définitivement enregistrées
					} catch (java.sql.SQLException e){
						e.printStackTrace();
					}
			}	
		} catch (java.sql.SQLException e){
			e.printStackTrace();
		}	
			
		
		
	}		
}
