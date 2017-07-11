package fr.epita.iam.test.services;
/**
 * @author Nwadishi
 *
 */
import java.sql.SQLException;
import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.DAOCreateException;
import fr.epita.iam.exceptions.DAOSearchException;
import fr.epita.iam.services.IdentityDAO;
import fr.epita.iam.services.JDBCIdentityDAO;




public class TestJDBCIdentityDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
	            IdentityDAO identityDAO = new JDBCIdentityDAO();
	            Identity identity = new Identity("thomas", "xyz@gmail.com", null);

	            try {
	                identityDAO.create(identity);
	            } catch (DAOCreateException e) {
	                e.printStackTrace();
	            }

	            try {
	                List<Identity> results = identityDAO.search(new Identity(null, null, null));
	                for (Identity result : results) {
	                    System.out.println(result);
	                }
	            } catch (DAOSearchException e) {
	                e.printStackTrace();
	            }

	            identityDAO.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
