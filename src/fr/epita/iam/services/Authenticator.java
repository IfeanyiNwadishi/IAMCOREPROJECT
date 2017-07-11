package fr.epita.iam.services;
/**
 * @author Nwadishi
 *
 */
public interface Authenticator {
	/**
     * Authenticate with username and password.
     * @param username 
     * @param password 
     */
    boolean authenticate(String username, String password);
}