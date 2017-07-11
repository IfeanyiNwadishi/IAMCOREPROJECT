package fr.epita.iam.services;
/**
 * @author Nwadishi
 *
 */
import fr.epita.iam.configuration.Configuration;

public class Authentication implements Authenticator {
	 private String username;
	    private String password;

	    public Authentication() {
	        this.username = Configuration.authenticateUsername;
	        this.password = Configuration.authenticatePassword;
	    }

	    /**
	     * @param username 
	     * @param password 
	     */
	    public boolean authenticate(String username, String password) {
	        return username.equals(this.username) && password.equals(this.password);
	    }

}
