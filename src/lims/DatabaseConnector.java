package lims;

/**
 *
 * @author Josh
 */

import java.sql.*;

public class DatabaseConnector {
    
    //This makes a connection to the given database
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
    
    //This lists the users in the database
    public String viewUsers(Connection con) throws SQLException, Exception{
         
        //Make a blank output string
        String output = "";
        
        //Create the select query
        String query = "SELECT * FROM users";
        
        if(con != null){
            //Create the java statement
            Statement st = con.createStatement();

            //Execute the query and get a java resultset
            ResultSet rs = st.executeQuery(query);

            //Parse the resultset
            while (rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");

                output += "ID: " + id + "\nUsername: " + username + "\nPassword: " + password + "\n";
            }

            //Close the statement
            st.close();
            
            return output;
        }
        else{
            throw new Exception("Cannot execute statement: database connector is NULL");
        }
    }
    
    //Make new project (table)
    public void newProject(Connection con, String newProjectName) throws SQLException{
        
        //Make a query
        String query = "CREATE TABLE `lims`.`" + newProjectName + "` (\n" + "  `id` INT NOT NULL AUTO_INCREMENT,\n" + "  PRIMARY KEY (`id`),\n" + "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)";
        
        //You will need to add 
        if(con != null){
            //Create the java statement
            Statement st = con.createStatement();

            //Execute the query
            st.executeUpdate(query);
            
            //Create query for entry into projectnames table
            String entryQuery = "INSERT INTO projectnames (projectName) VALUES (\'" + newProjectName + "\')";
            
            //Insert the projectnames table query
            st.executeUpdate(entryQuery);
            
            //Close the statement
            st.close();
        }
        
        //Add new project to projectnames table 
    }
    
    //Check Login Information Against Database Credentials
    public boolean  confirmAuthentication(Connection con, String username, String password) throws SQLException, Exception{
        //Make a blank output string
        String output = "";
        
        //Create the select query
        String query = "SELECT * FROM users";
        
        if(con != null){
            //Create the java statement
            Statement st = con.createStatement();

            //Execute the query and get a java resultset
            ResultSet rs = st.executeQuery(query);

            //Parse the resultset
            while (rs.next()){
                String uname = rs.getString("username");
                String pass = rs.getString("password");
                
                if(uname.equals(username) && pass.equals(password)){
                    return true;
                }
            }
            //Close the statement
            st.close();
        }
        throw new Exception("Cannot execute statement: database connector is NULL");
    }
    
    public void audit(Connection con,String username, String projectName,String actionTaken) throws SQLException{
        
        //Create the insert query
        String query = "INSERT INTO audit (username,projectName,actionTaken) VALUES (\'" + username + "\',\'" + projectName + "\',\'" + actionTaken + "\')";
        
        if(con != null){
            
            //Create the java statement
            Statement st = con.createStatement();
            
            //Execute the query
            st.executeUpdate(query);
            
            //Close the statement
            st.close();
        }

    }
    
    //This is a genericRead on a column. It can only read strings, and it should be edited to have a WHERE sampleID == IDOFPAGE or something
    public String genericRead(Connection con,String table,String columnToRead) throws SQLException, Exception{
              
        //Make a blank output string
        String output = "";
        
        //Create the select query
        String query = "SELECT " + columnToRead + " FROM " + table;
        
        if(con != null){
            //Create the java statement
            Statement st = con.createStatement();

            //Execute the query and get a java resultset
            ResultSet rs = st.executeQuery(query);

            //Parse the resultset
            while (rs.next()){
                String itemRead = rs.getString(columnToRead);

                output += itemRead + "\n";
            }
            
            //Close the statement
            st.close();
            return output;
        }
        else{
            throw new Exception("Cannot execute statement: database connector is NULL");
        }
    }
}
