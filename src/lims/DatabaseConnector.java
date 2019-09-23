package lims;

/**
 *
 * @author Josh
 */

import java.sql.*;

public class DatabaseConnector {
    
    private Connection con;
    
    public DatabaseConnector(Connection connection){
        con = connection;
    }
    
    public DatabaseConnector(){
        con = null;
    }
    
    
    //This makes a connection to the given database
    public void makeConection(String ip,String port, String databaseName, String username,String password) throws SQLException, Exception{
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + databaseName,username,password);
            
            con = connection;
        } 
        catch (Exception e) {
           System.out.println(e);
        }
    }
    
    //This closes the database conenction
    public void closeConnection() throws SQLException{
        con.close();
    }
    
    //This lists the users in the database
    public String viewUsers() throws SQLException, Exception{
         
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
    public void newProject(String newProjectName) throws SQLException{
        
        //Make a query
        String query = "CREATE TABLE `" + newProjectName +"` (" +
            "`recordNumber` int(11) NOT NULL AUTO_INCREMENT," +
            "`legionellaStatus` varchar(255) DEFAULT NULL," +
            "`dateInProcessing` datetime DEFAULT NULL," +
            "`timeInProcessing` datetime DEFAULT NULL," +
            "`personInProcessing` varchar(255) DEFAULT NULL," +
            "`blankInformation` varchar(255) DEFAULT NULL," +
            "`fieldID` varchar(255) DEFAULT NULL," +
            "`SIN` varchar(255) DEFAULT NULL," +
            "`colorID` varchar(255) DEFAULT NULL," +
            "`waterType` varchar(255) DEFAULT NULL," +
            "`abnormailitiesInProcessing` varchar(255) DEFAULT NULL," +
            "`incubator` varchar(255) DEFAULT NULL," +
            "`detectionLimit` varchar(255) DEFAULT NULL," +
           "`conditionOfBox` varchar(255) DEFAULT NULL," +
            "`analystMembraneConcentration` varchar(255) DEFAULT NULL," +
            "`dateMembraneConcentration` datetime DEFAULT NULL," +
            "`timeMembraneConcentration` datetime DEFAULT NULL," +
            "`abnormalitiesMembraneConcentration` varchar(255) DEFAULT NULL," +
            "`analystTreatment` varchar(255) DEFAULT NULL," +
            "`dateTreatment` datetime DEFAULT NULL," +
            "`timeTreatment` datetime DEFAULT NULL," +
            "`abnormalitiesTreatment` varchar(255) DEFAULT NULL," +
            "`analystInoculation` varchar(255) DEFAULT NULL," +
            "`dateIncoulation` datetime DEFAULT NULL," +
            "`timeInoculation` datetime DEFAULT NULL," +
            "`abnormailitiesInoculation` varchar(255) DEFAULT NULL," +
            "`analystNT` varchar(255) DEFAULT NULL," +
            "`dateNT` datetime DEFAULT NULL," +
            "`dayReadNT` int(11) DEFAULT NULL," +
            "`observationsNT` varchar(255) DEFAULT NULL," +
            "`observationActionNT` varchar(255) DEFAULT NULL," +
            "`analystAT` varchar(255) DEFAULT NULL," +
            "`dateAT` datetime DEFAULT NULL," +
            "`dayReadAT` int(11) DEFAULT NULL," +
            "`observationsAT` varchar(255) DEFAULT NULL," +
            "`observationActionAT` varchar(255) DEFAULT NULL," +
            "`enumerationNT` int(11) DEFAULT NULL," +
            "`enumerationAT` int(11) DEFAULT NULL," +
            "`analystHT` varchar(255) DEFAULT NULL," +
            "`dateHT` datetime DEFAULT NULL," +
            "`datReadHT` int(11) DEFAULT NULL," +
            "`observationsHT` varchar(255) DEFAULT NULL," +
            "`observationActionHT` varchar(255) DEFAULT NULL," +
            "`enumerationHT` int(11) DEFAULT NULL," +
            "`analystBCYE` varchar(255) DEFAULT NULL," +
            "`dateBCYE` datetime DEFAULT NULL," +
            "`postDaysBCYE` int(11) DEFAULT NULL," +
            "`bcyeLot` varchar(255) DEFAULT NULL," +
            "`bcyeCYSTLot` varchar(255) DEFAULT NULL," +
            "`treatedPlateSubbed` varchar(255) DEFAULT NULL," +
            "`observationBCYE` varchar(255) DEFAULT NULL," +
            "`analystAgglutination` varchar(255) DEFAULT NULL," +
            "`dateAgglutination` datetime DEFAULT NULL," +
            "`postDaysAgglutination` int(11) DEFAULT NULL," +
            "`hydrationLogAgglutination` varchar(255) DEFAULT NULL," +
            "`serogroup1Agglutination` varchar(255) DEFAULT NULL," +
            "`serogroup2to15Agglutination` varchar(255) DEFAULT NULL," +
            "`speciesLotAgglutination` varchar(255) DEFAULT NULL," +
            "`observationAgglutination` varchar(255) DEFAULT NULL," +
            "`analystPerformed110Dillution` varchar(255) DEFAULT NULL," +
            "`datePerformed110Dillution` datetime DEFAULT NULL," +
            "`postDaysPerformed110Dillution` int(11) DEFAULT NULL," +
            "`time110Dillution` datetime DEFAULT NULL," +
            "`abnormailities110Dillution` varchar(255) DEFAULT NULL," +
            "`analystRead110Dillution` varchar(255) DEFAULT NULL," +
            "`dateRead110Dillution` datetime DEFAULT NULL," +
            "`postDaysRead110Dillution` int(11) DEFAULT NULL," +
            "`observationsRead110Dillution` varchar(255) DEFAULT NULL," +
            "`analystPerformed1100Dillution` varchar(255) DEFAULT NULL," +
            "`datePerformed1100Dillution` datetime DEFAULT NULL," +
            "`timePerformed1100Dillution` datetime DEFAULT NULL," +
            "`abnormalitiesPerformed1100Dillution` varchar(255) DEFAULT NULL," +
            "`analystRead1100Dillution` varchar(255) DEFAULT NULL," +
            "`dateRead1100Dillution` datetime DEFAULT NULL," +
            "`postDaysRead1100Dillution` int(11) DEFAULT NULL," +
            "`postDaysPerformed1100Dillution` int(11) DEFAULT NULL," +
            "`observationsRead1100Dillution` varchar(255) DEFAULT NULL," +
            "PRIMARY KEY (`recordNumber`)" +
          ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
        
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
        
    }
    
    //Check Login Information Against Database Credentials
    public boolean  confirmAuthentication(String username, String password) throws SQLException, Exception{
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
    
    public void audit(String username, String projectName,String actionTaken) throws SQLException{
        
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
    public String genericRead(String table,String columnToRead) throws SQLException, Exception{
              
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
    
    //This is a generic write to a table. It will only make a new row and only if you use the primary key as the write (and it's not Auto Increment)
    public void genericWriteString(String table, String columnToWrite,String value) throws SQLException{
        //Make sure connection is valid
        if(con != null){
            //Create the java statement
            Statement st = con.createStatement();

            //Create query for entry into table
            String query = "INSERT INTO " + table + " (" + columnToWrite + ") VALUES (\'" + value + "\')";

            //Execute the query
            st.executeUpdate(query);
            
            //Close the statement
            st.close();
        }
    }
   
    //This is a generic update. This will allow you to change a row. Might be able to make this generic by using Object instead of string.
    public void genericUpdateString(String table, String primaryKeyColumn, String rowIdentifier, String columnToUpdate, String value) throws SQLException{
        //Make sure connection is valid
        if(con != null){
            //Create the java statement
            Statement st = con.createStatement();

            //Create query for update into table
            String query = "UPDATE " + table + " SET " + columnToUpdate + " = \'" + value + "\' WHERE (" + primaryKeyColumn + " = \'" + rowIdentifier + "\');" ;

            //Execute the query
            st.executeUpdate(query);
            
            //Close the statement
            st.close();
        }
    }
    
    //GETTERS
    public Connection getConnection(){
        return con;
    }
}
