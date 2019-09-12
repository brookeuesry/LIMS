package lims;

import java.sql.SQLException;
import java.sql.*;
/**
 *
 * @author Josh
 */
public class LIMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, Exception {
        // TODO code application logic here
        
        DatabaseConnector connector = new DatabaseConnector();
        
        Connection connection = connector.makeConection("localhost", "3306", "lims", "admin", "hgsit!");
        
        System.out.println(connector.viewUsers(connection));
        
        connection.close();
    }
}
