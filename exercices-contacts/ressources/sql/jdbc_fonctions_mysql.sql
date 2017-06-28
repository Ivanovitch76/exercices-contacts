START TRANSACTION;

DROP FUNCTION IF EXISTS JdbcGetEmailByContact;
DROP PROCEDURE IF EXISTS JdbcGetEmailsByAbreviation;
DROP PROCEDURE IF EXISTS JdbcGetTagsByContact;
DROP FUNCTION IF EXISTS JdbcCreateContact;
DROP PROCEDURE IF EXISTS JdbcAddTagToContact;
DROP PROCEDURE IF EXISTS JdbcRemoveContact;

DELIMITER $$

CREATE FUNCTION JdbcGetEmailByContact(prenomContact TEXT, nomContact TEXT)
	RETURNS TEXT
	BEGIN
		DECLARE mail TEXT;
		SELECT c.email INTO mail FROM contacts c WHERE LOWER(c.prenom) = LOWER(prenomContact) AND LOWER(c.nom) = LOWER(nomContact);
		RETURN mail;
	END;
$$

CREATE PROCEDURE JdbcGetEmailsByAbreviation(abreviationPays TEXT) 
	BEGIN
		SELECT c.email FROM contacts c JOIN pays p ON c.pays = p.id WHERE LOWER(p.abreviation) = LOWER(abreviationPays);
	END;
$$
	
CREATE PROCEDURE JdbcGetTagsByContact(prenomContact TEXT, nomContact TEXT)
	BEGIN
		SELECT t.tag FROM tags t JOIN contacts_tags ct ON t.id = ct.tag JOIN contacts c ON ct.contact = c.id
				WHERE LOWER(c.prenom) = LOWER(prenomContact) AND LOWER(c.nom) = LOWER(nomContact);	
	END;	
$$

CREATE FUNCTION JdbcCreateContact(cprenom TEXT, cnom TEXT, cemail TEXT, pabreviation TEXT) RETURNS INTEGER
	BEGIN
		DECLARE idContact INTEGER;
		DECLARE tmpId INTEGER;
		DECLARE idPays INTEGER;
		SET idContact = 0;
		IF cprenom IS NOT NULL AND cnom IS NOT NULL AND cemail IS NOT NULL THEN
			SELECT c.id INTO tmpId FROM contacts c WHERE LOWER(c.prenom) = LOWER(cprenom) AND LOWER(c.nom) = LOWER(cnom);
			IF tmpId IS NULL THEN
				IF pabreviation IS NOT NULL THEN
					SELECT p.id INTO idPays FROM pays p WHERE LOWER(p.abreviation) = LOWER(pabreviation);
				END IF;
				IF pabreviation IS NULL OR idPays IS NOT NULL THEN
					INSERT INTO contacts(prenom, nom, email, pays) VALUES(cprenom, cnom, cemail, idPays);
					SET idContact = LAST_INSERT_ID();
				END IF;
			END IF;
		END IF;
		RETURN idContact;
	END;
$$

CREATE PROCEDURE JdbcAddTagToContact(idContact INTEGER, tagValue TEXT) 
	BEGIN
		DECLARE idTag INTEGER;
		IF idContact IS NULL OR tagValue IS NULL THEN
			SIGNAL SQLSTATE '99999' SET MESSAGE_TEXT='les paramètres ne peuvent pas être null';
		ELSE
			SELECT t.id INTO idTag FROM tags t WHERE LOWER(t.tag) = LOWER(tagValue);
			IF idTag IS NULL THEN
				INSERT INTO tags(tag) VALUES(tagValue);
				SET idTag = LAST_INSERT_ID();
			END IF; 
			INSERT INTO contacts_tags(contact, tag) VALUES(idContact, idTag);
		END IF;
	END;
$$

CREATE PROCEDURE JdbcRemoveContact(idContact INTEGER)
	BEGIN
		DELETE FROM contacts WHERE id = idContact;	
	END;
$$

DELIMITER ;

COMMIT;