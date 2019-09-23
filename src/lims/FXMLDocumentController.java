/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lims;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Logan
 */
public class FXMLDocumentController implements Initializable  {
    
    public DatabaseConnector connector = new DatabaseConnector();
    
    @FXML
    Button exit;
    @FXML
    Button login;
    @FXML
    TextField username_field;
    @FXML
    TextField password_field;
    
    @FXML
    private void handleButtonAction(ActionEvent event){
        System.exit(1);
    }
    
    @FXML
    private void submitLogin(ActionEvent event) throws Exception {
        
        String username = username_field.getText();
        String password = password_field.getText();
        connector.makeConection("192.168.1.29", "3306", "lims", "admin", "hgsit!");
        
        if(connector.confirmAuthentication(username, password)){
            System.out.println("Log-In Success");
            Parent pane = FXMLLoader.load(getClass().getResource("receivingPage.fxml"));
            Scene scene = new Scene( pane );
            Stage curStage = (Stage) login.getScene().getWindow();
            curStage.setScene(scene);
            
        }else{
            System.out.println("Log-In Failed");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
