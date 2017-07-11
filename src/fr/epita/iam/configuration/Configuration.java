package fr.epita.iam.configuration;
/**
 * @author Nwadishi
 *This is the configuration class that authenticates the connection to the database
 */
public class Configuration {
	/**
     * JDBC Connection string.
     */
    public static final String jdbcURL = "jdbc:derby://localhost:1527/hify";

    /**
     * JDBC Username 
     */
    public static final String jdbcUsername = "admin";

    /**
     * JDBC Password 
     */
    public static final String jdbcPassword = "admin";

    /**
     * Authentication class Username.
     */
    public static final String authenticateUsername = "admin";

    /**
     * Authentication class Password.
     */
    public static final String authenticatePassword = "admin";


}
