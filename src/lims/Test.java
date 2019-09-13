package lims;

import java.sql.Connection;

/**
 *
 * @author Josh
 */
public class Test {
    
    //For database testing
    public void databaseTests() throws Exception{
        //Below is test code for the Database Connector and its various functions. This will need to be moved to a databse test class or regular test class
        DatabaseConnector connector = new DatabaseConnector();
        Connection connection = connector.makeConection("192.168.1.29", "3306", "lims", "admin", "hgsit!");
        
        String projectName = "testProject1111";
        String username = "Logan";
        String password = "password1";
        
        connector.newProject(connection,projectName);//Test new project creation
        
        System.out.println("AUTHENTICATION TEST: " + connector.confirmAuthentication(connection, username, password));//Test user authentication
        
        connector.audit(connection, username, projectName,"TEST AUDIT");//Test audit
        
        System.out.println("GENERIC DATABASE READ ON USERS TABLE WITH USERNAME COLUMN:" + connector.genericRead(connection, "users", "username"));//Test generic database read
        
        connection.close();
    }
    
    //Encryption Test
    public void encryptionTest(){
        String password = "password1";
        
        //ENCRYPTION TESTS
        Encryption encrypt = new Encryption();
        String encryptedPassword = encrypt.encrypt(password); 
        
        System.out.println("Unencrypted password is: " + password);
        System.out.println("Encrypted password is: " + encryptedPassword);
        System.out.println("Decrypted password is: " + encrypt.decrypt(encryptedPassword));
    }
    
    
}
