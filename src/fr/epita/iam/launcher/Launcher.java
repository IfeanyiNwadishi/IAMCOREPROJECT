package fr.epita.iam.launcher;
/**
 * /**
 * @author Nwadishi
 * 
  */
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.DAOCreateException;
import fr.epita.iam.exceptions.DAODeleteException;
import fr.epita.iam.exceptions.DAOSearchException;
import fr.epita.iam.exceptions.DAOUpdateException;
import fr.epita.iam.services.Authentication;
import fr.epita.iam.services.Authenticator;
import fr.epita.iam.services.IdentityDAO;
import fr.epita.iam.services.JDBCIdentityDAO;


public class Launcher {
	private static Scanner scan;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 scan = new Scanner(System.in);

	        System.out.println("Welcome to I AM CORE");

	        if (!authenticate()) {
	            System.out.println("Authenticate failed.");
	            System.exit(0);
	        }

	        System.out.println("Authenticate successful.");

	        mainProgram();
	    }

	    private static boolean authenticate() {
	        Authenticator authenticator = new Authentication();

	        System.out.println("Welcome to I AM CORE:");
	        System.out.println("Please login to proceed:");
	        System.out.println("Insert your Username: ");
	        String username = scan.nextLine();
	        System.out.println("Insert your Password: ");
	        String password = scan.nextLine();

	        return authenticator.authenticate(username, password);
	    }

	    private static void mainProgram() {
	        System.out.println("");
	        System.out.println("Welcome!!!:");
	        System.out.println("What would you like to do?:");
	        System.out.println("1) Create new Identity");
	        System.out.println("2) Search Identities");
	        System.out.println("3) Update Identity");
	        System.out.println("4) Delete Identity");
	        System.out.println("5) Exit");

	        System.out.println("Please select an option 1,2,3,4,5: ");

	        String task = scan.nextLine().trim();

	        switch (task) {
	            case "1":
	                createIdentityMenu();
	                break;
	            case "2":
	                searchIdentityMenu();
	                break;
	            case "3":
	                updateIdentityMenu();
	                break;
	            case "4":
	                deleteIdentityMenu();
	                break;
	            case "5":
	                System.exit(0);
	                break;
	            default:
	                System.out.println("Unrecognised task.");
	                mainProgram();
	        }
	    }

	    private static void createIdentityMenu() {
	        System.out.println("Create new identity: ");
	        System.out.println("Identity Name: ");
	        String displayname = scan.nextLine();
	        System.out.println("Identity Email: ");
	        String email = scan.nextLine();
	        Identity identity = new Identity(displayname, email, null);

	        createIdentity(identity);
	        mainProgram();
	    }

	    private static void searchIdentityMenu() {
	        System.out.println("Search identities (Insert UID) ");
	        System.out.println("UID: ");
	        String uid = scan.nextLine();
	        System.out.println("Identity Name: ");
	        String displayname = scan.nextLine();
	        System.out.println("Identity Email: ");
	        String email = scan.nextLine();
	        Identity identity = new Identity(null, null, null);

	        if (uid.length() != 0) {
	            identity.setUid(Integer.parseInt(uid));
	        }

	        if (displayname.length() != 0) {
	            identity.setDisplayName(displayname);
	        }

	        if (email.length() != 0) {
	            identity.setEmail(email);
	        }

	        searchIdentity(identity);
	        mainProgram();
	    }

	    private static void updateIdentityMenu() {
	        System.out.println("Update Identity:");
	        Identity identity = new Identity(null, null, null);

	        searchIdentity(identity);
	        System.out.println("Select Identity by UID:");
	        int uid = Integer.parseInt(scan.nextLine());
	        identity.setUid(uid);
	        System.out.println("New Display Name:");
	        identity.setDisplayName(scan.nextLine());
	        System.out.println("New Email:");
	        identity.setEmail(scan.nextLine());

	        updateIdentity(identity);

	        mainProgram();
	    }

	    private static void deleteIdentityMenu() {
	        System.out.println("Delete Identity:");
	        Identity identity = new Identity(null, null, null);

	        searchIdentity(identity);
	        System.out.println("Select Identity by UID:");
	        int uid = Integer.parseInt(scan.nextLine());
	        identity.setUid(uid);

	        deleteIdentity(identity);

	        mainProgram();
	    }

	    private static void createIdentity(Identity identity) {
	        try {
	            IdentityDAO dao = new JDBCIdentityDAO();
	            try {
	                dao.create(identity);
	                System.out.println("You have Successfully created a new identity!");
	            } catch (DAOCreateException e) {
	                e.printStackTrace();
	            } finally {
	                dao.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void searchIdentity(Identity criteria) {
	        try {
	            IdentityDAO dao = new JDBCIdentityDAO();
	            try {
	                List<Identity> results = dao.search(criteria);
	                for (Identity result: results) {
	                    System.out.println(result);
	                }
	            } catch (DAOSearchException e) {
	                e.printStackTrace();
	            } finally {
	                dao.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void updateIdentity(Identity identity) {
	        try {
	            IdentityDAO dao = new JDBCIdentityDAO();
	            try {
	                dao.update(identity);
	                System.out.println("You have Successfully updated an identity!");
	            } catch (DAOUpdateException e) {
	                e.printStackTrace();
	            } finally {
	                dao.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void deleteIdentity(Identity identity) {
	        try {
	            IdentityDAO dao = new JDBCIdentityDAO();
	            try {
	                dao.delete(identity);
	                System.out.println("You have Successfully deleted an identity!");
	            } catch (DAODeleteException e) {
	                e.printStackTrace();
	            } finally {
	                dao.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }



}

