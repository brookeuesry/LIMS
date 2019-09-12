package lims;

/**
 *
 * @author Josh
 */

import java.sql.*;

public class DatabaseConnector {
    
    //Use this to make a connection
    public Connection makeConection(String ip,String port, String databaseName, String username,String password) throws SQLException, Exception{
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + databaseName,username,password);
            
            return con;
        } 
        catch (Exception e) {
           System.out.println(e);
        }
        throw new Exception("CONNECTOR IS NULL");
    }
    
    public String viewUsers(Connection con) throws SQLException, Exception{
         
        //Make a blank output string
        String output = "";
        
        //Create the select query
        String query = "SELECT * FROM testusers";
        
        if(con != null){
            //Create the java statement
            Statement st = con.createStatement();

            //Execute the query and get a java resultset
            ResultSet rs = st.executeQuery(query);

            //Parse the resultset
            while (rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("name");
                String password = rs.getString("password");

                output += "ID: " + id + "\nUsername: " + username + "\nPassword: " + password + "\n";
            }

            return output;
        }
        else{
            throw new Exception("Cannot execute statement: database connector is NULL");
        }
    }
}
