package fr.epita.iam.services;
/**
 * @author Nwadishi
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.iam.configuration.Configuration;
import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.DAOCreateException;
import fr.epita.iam.exceptions.DAODeleteException;
import fr.epita.iam.exceptions.DAOSearchException;
import fr.epita.iam.exceptions.DAOUpdateException;



public class JDBCIdentityDAO implements IdentityDAO {
	private Connection conn;

    public JDBCIdentityDAO() throws SQLException {
        this.conn = DriverManager.getConnection(
                Configuration.jdbcURL,
                Configuration.jdbcUsername,
                Configuration.jdbcPassword
        );
    }

    /**
     * Stores the Identity to Identity table.
     */
    public void create(Identity identity) throws DAOCreateException {
        try {
            PreparedStatement statement = this.conn.prepareStatement(
                    "INSERT INTO IDENTITIES(IDENTITY_DISPLAYNAME, IDENTITY_EMAIL) VALUES (?, ?)"
            );
            statement.setString(1, identity.getDisplayName());
            statement.setString(2, identity.getEmail());
            statement.execute();
        } catch (SQLException sqle) {
            DAOCreateException e = new DAOCreateException();
            e.initCause(sqle);
            e.setFaultObject(identity);
            throw e;
        }
    }

    /**
     * Search the Identity from Identity table.
     */
    public List<Identity> search(Identity criteria) throws DAOSearchException {
        List<Identity> results = new ArrayList<>();
        String sql = "SELECT * FROM IDENTITIES";
        boolean searching = false;

        if (criteria.getUid() != null) {
            sql += " WHERE IDENTITY_UID=" + criteria.getUid();
            searching = true;
        }

        if (criteria.getDisplayName() != null) {
            if (searching) {
                sql += " AND IDENTITY_DISPLAYNAME='" + criteria.getDisplayName() + "'";
            } else {
                sql += " WHERE IDENTITY_DISPLAYNAME='" + criteria.getDisplayName() + "'";
            }
            searching = true;
        }

        if (criteria.getEmail() != null) {
            if (searching) {
                sql += " AND IDENTITY_EMAIL='" + criteria.getEmail() + "'";
            } else {
                sql += " WHERE IDENTITY_EMAIL='" + criteria.getEmail() + "'";
            }
        }

        try {
            PreparedStatement statement = this.conn.prepareStatement(sql);
            ResultSet statementResult = statement.executeQuery();
            while (statementResult.next()) {
                String displayname = statementResult.getString("IDENTITY_DISPLAYNAME");
                String email = statementResult.getString("IDENTITY_EMAIL");
                int uid = statementResult.getInt("IDENTITY_UID");
                results.add(new Identity(displayname, email, uid));
            }
        } catch (SQLException sqle) {
            DAOSearchException e = new DAOSearchException();
            e.initCause(sqle);
            e.setFaultObject(criteria);
            throw e;
        }

        return results;
    }

    /**
     * Update Identity to Identity table.
     */
    public void update(Identity identity) throws DAOUpdateException {
        try {
            PreparedStatement statement = this.conn.prepareStatement(
                    "UPDATE IDENTITIES SET IDENTITY_DISPLAYNAME=?, IDENTITY_EMAIL=? WHERE IDENTITY_UID=?"
            );
            statement.setString(1, identity.getDisplayName());
            statement.setString(2, identity.getEmail());
            statement.setInt(3, identity.getUid());
            statement.execute();
        } catch (SQLException sqle) {
            DAOUpdateException e = new DAOUpdateException();
            e.initCause(sqle);
            e.setFaultObject(identity);
            throw e;
        }
    }

    /**
     * Delete Identity from Identity table.
     */
    public void delete(Identity identity) throws DAODeleteException {
        try {
            PreparedStatement statement = this.conn.prepareStatement(
                    "DELETE FROM IDENTITIES WHERE IDENTITY_UID=?"
            );
            statement.setInt(1, identity.getUid());
            statement.execute();
        } catch (SQLException sqle) {
            DAODeleteException e = new DAODeleteException();
            e.initCause(sqle);
            e.setFaultObject(identity);
            throw e;
        }
    }

    /**
     * Close the JDBC connection.
     */
    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
