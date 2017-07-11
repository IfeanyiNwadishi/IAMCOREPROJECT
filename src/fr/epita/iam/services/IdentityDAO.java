package fr.epita.iam.services;
/**
 * @author Nwadishi
 *
 */
import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.DAOCreateException;
import fr.epita.iam.exceptions.DAODeleteException;
import fr.epita.iam.exceptions.DAOSearchException;
import fr.epita.iam.exceptions.DAOUpdateException;


public interface IdentityDAO {
	 /**
     * Creates the Identity to Database.
     */
    void create(Identity identity) throws DAOCreateException;

    /**
     * Search or read Identity from Database with given criteria.
     */
    List<Identity> search(Identity criteria) throws DAOSearchException;

    /**
     * Update Identity in Database.
     */
    void update(Identity identity) throws DAOUpdateException;

    /**
     * Delete Identity from Database.
     */
    void delete(Identity identity) throws DAODeleteException;

    /**
     * Close the access to the Database.
     */
    void close();


}
