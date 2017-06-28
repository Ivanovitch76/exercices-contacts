package be.steformations.pc.java_data.contacts.jdbc;

import be.steformations.java_data.contacts.interfaces.jdbc.ContactJdbcManager;

public class ContactJdbcManagerWithoutSqlStoredProcedures implements ContactJdbcManager {

	private final String url;
	private final String login;
	private final String pwd;
	
	public ContactJdbcManagerWithoutSqlStoredProcedures(String url, String login, String pwd) {
		super();
		this.url = url;
		this.login = login;
		this.pwd = pwd;
	}

	@Override
	public String getEmailByContact(String firstname, String name) {
		
		String email = null;
		
		if ( firstname != null && name != null ) {

			String sql = "SELECT * FROM contacts "
					   + "WHERE LOWER(prenom) = LOWER(?) AND LOWER(nom) = LOWER(?)";
			
			try (
				java.sql.Connection connection = java.sql.DriverManager.getConnection(url, login, pwd);
				java.sql.PreparedStatement query = connection.prepareStatement(sql);
			) {
			
				query.setString(1, firstname);
				query.setString(2, name);

				try (
					java.sql.ResultSet rs = query.executeQuery();		
				) {
					if ( rs.next() ) {
						email = rs.getString("email");
					}
				} 
				
			} catch(java.sql.SQLException ex) {
				ex.printStackTrace();
			}
			
		}
		
		return email;
	}

	@Override
	public java.util.List<String> getEmailsByCountry(String abbreviation) {
		
		java.util.List<String> emails = new java.util.ArrayList<String>();
		
		if ( abbreviation != null ) {

			String sql = "SELECT c.email " +
					     "FROM contacts c JOIN pays p ON c.pays = p.id " +
					     "WHERE LOWER(p.abreviation) = LOWER(?) ";
			
			try (
				java.sql.Connection connection = java.sql.DriverManager.getConnection(url, login, pwd);
				java.sql.PreparedStatement query = connection.prepareStatement(sql);
			) {
				query.setString(1, abbreviation);
				
				try (
					java.sql.ResultSet rs = query.executeQuery();
				) {
					while ( rs.next() ) {
						emails.add(rs.getString("email"));
					}
				}
				
			} catch (java.sql.SQLException e) {
				e.printStackTrace();
			}
		} 
		
		return emails;
	}

	@Override
	public java.util.List<String> getTagsByContact(String firstname, String name) {
		
		java.util.List<String> tags = new java.util.ArrayList<String>();
		
		if ( firstname != null && name != null ) {
		
			String sql = "SELECT t.tag "
					   + "FROM tags t JOIN contacts_tags ct ON ct.tag=t.id "
					   + "            JOIN contacts c ON ct.contact = c.id "
					   + "WHERE LOWER(c.prenom) = LOWER(?) AND LOWER(c.nom) = LOWER(?)";
			
			try (
				java.sql.Connection connection = java.sql.DriverManager.getConnection(url, login, pwd);
				java.sql.PreparedStatement query = connection.prepareStatement(sql);
			) {
				
				query.setString(1, firstname);
				query.setString(2, name);
				
				try (
					java.sql.ResultSet rs = query.executeQuery();		
				) {
					while ( rs.next() ) {
						tags.add(rs.getString(1));	
					}
				} 
				
			} catch(java.sql.SQLException e) {
				e.printStackTrace();
				tags = java.util.Collections.emptyList();
			}			
		} 
		
		return tags;
	}

	@Override
	public int createAndSaveContact(String firstname, String name,
			String email, String abbreviation, String[] tags) {
		
		int contactId = 0;
		
		if ( firstname != null && name != null && email != null ) {
		
			try (
				java.sql.Connection connection = java.sql.DriverManager.getConnection(url, login, pwd);
			) {
				connection.setAutoCommit(false);
				int pk = this.getContactIdByFirstnameAndName(connection, firstname, name);
				if ( pk == 0 ) {
					Integer countryId = null;
					if ( abbreviation == null 
							|| (countryId = this.getCountryIdFromAbbreviation(connection, abbreviation)) != 0) {								
						try {
							contactId = this.insertContact(connection, firstname, name, email, countryId);
							if ( tags != null && tags.length != 0 ) {
								this.insertTags(connection, contactId, tags);
							}
							connection.commit();
						} catch(java.sql.SQLException e) {
							connection.rollback();
							throw e;
						} 
					}
				}
				
			} catch(java.sql.SQLException e) {
				e.printStackTrace();
			}
		}
		
		return contactId;
	}
	
	@Override
	public void removeContact(int id) {

		String sql = "DELETE FROM contacts WHERE id=?";
		
		try(
			java.sql.Connection connection = java.sql.DriverManager.getConnection(url, login, pwd);
			java.sql.PreparedStatement query = connection.prepareStatement(sql);	
		) {
			connection.setAutoCommit(true);
			
			query.setInt(1, id);
			query.executeUpdate();
			
		} catch(java.sql.SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected int getContactIdByFirstnameAndName(java.sql.Connection connection, String firstname, String name) {
		
		int id = 0;
		String sql = "SELECT * FROM contacts WHERE LOWER(prenom) = LOWER(?) AND LOWER(nom) = LOWER(?)";
		
		try (
			java.sql.PreparedStatement query = connection.prepareStatement(sql);		
		) {
			query.setString(1, firstname);
			query.setString(2, name);
			
			try (
				java.sql.ResultSet rs = query.executeQuery();		
			) {
				if ( rs.next() ) { 
					id = rs.getInt("id");
				}
			}
			
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		} 
		
		return id;
	}

	protected int getCountryIdFromAbbreviation(java.sql.Connection connection, String abbreviation) {
		int id = 0;
	
		String sql = "SELECT * FROM pays WHERE abreviation = ?";
		
		try (
			java.sql.PreparedStatement query = connection.prepareStatement(sql);
		) {
			
			query.setString(1, abbreviation);
			
			try (
				java.sql.ResultSet rs = query.executeQuery();		
			){
				if ( rs.next() ) { 
					id = rs.getInt("id");
				}
			}
			
		} catch(java.sql.SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	protected int getTagIdFromValue(java.sql.Connection connection, String value) {
		int id = 0;
		
		String sql = "SELECT * FROM tags WHERE tag = ?";
		
		try (
			java.sql.PreparedStatement query = connection.prepareStatement(sql);
		) {
			query.setString(1, value);
			
			try (
				java.sql.ResultSet rs = query.executeQuery();
			) {
				if ( rs.next() ) { 
					id = rs.getInt("id");
				}
			}
			
		} catch(java.sql.SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	protected int insertOrGetTag(java.sql.Connection connection, String value) 
			throws java.sql.SQLException {
		
		int id = this.getTagIdFromValue(connection, value);
		
		if ( id == 0 ) {
			String sql = "INSERT INTO tags(tag) VALUES(?)";
			try (
				java.sql.PreparedStatement query
					= connection.prepareStatement(sql);
			){
				query.setString(1, value);
				query.executeUpdate();
				id = this.getTagIdFromValue(connection, value);
			
			} catch (java.sql.SQLException e) {
				throw e;
			} 
		}
		
		return id;
	}
	
	protected void linkContactAndTag(java.sql.Connection connection, int contactId, int tagId) 
			throws java.sql.SQLException {
		
		String sql = "INSERT INTO contacts_tags(contact, tag) VALUES(?, ?)";
		
		try (
			java.sql.PreparedStatement query = connection.prepareStatement(sql);
		) {
			query.setInt(1, contactId);
			query.setInt(2, tagId);
			query.executeUpdate();
		} 
	}
	
	protected void insertTags(java.sql.Connection connection, int contactId, String[] tagsValues) 
			throws java.sql.SQLException {
		
		for (String value : tagsValues) {
			int tagId = this.insertOrGetTag(connection, value);
			this.linkContactAndTag(connection, contactId, tagId);
		}
	}
	
	protected int insertContact(java.sql.Connection connection, String firstname, String name,
			String email, Integer countryId) throws java.sql.SQLException {
		
		int id = 0;
		String sql = "INSERT INTO contacts(prenom, nom, email, pays) " +
					 "	VALUES(?, ?, ?, ?)";
		
		try (
			java.sql.PreparedStatement query 
				= connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);		
		) {
			query.setString(1, firstname);
			query.setString(2, name);				
			query.setString(3, email);
			
			if ( countryId != null ) {
				query.setInt(4, countryId);
			} else {
				query.setNull(4, java.sql.Types.INTEGER);
			}
			
			query.executeUpdate();
			
			try (
				java.sql.ResultSet rs = query.getGeneratedKeys();		
			) {
				if ( rs.next() ) {
					id = rs.getInt(1);
				}
			}
		}
		return id;
	}

}
