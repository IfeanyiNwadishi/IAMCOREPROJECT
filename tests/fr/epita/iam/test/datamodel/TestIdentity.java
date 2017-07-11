package fr.epita.iam.test.datamodel;

import fr.epita.iam.datamodel.Identity;

/**
 * @author Nwadishi
 */
public class TestIdentity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   Identity identity = new Identity("admin", "nezykel@yahoo.com", 1);
	        System.out.println(identity);
	}

}
