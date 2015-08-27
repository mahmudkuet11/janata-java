/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mashape.unirest.http.Unirest;
import helpers.MetaData;
import helpers.Msg;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class LoginController implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onLoginButtonClick(ActionEvent event) {
        try {
            String username = this.username.getText();
            String password = this.password.getText();
            
            String res = Unirest.get(MetaData.baseUrl + "login")
                    .queryString("username", username)
                    .queryString("password", password)
                    .asString().getBody();
            if(res.equals("ok")){
                Parent root = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
                Scene scene = this.username.getScene();
                Stage stage = (Stage)this.username.getScene().getWindow();
                scene.setRoot(root);
                stage.setScene(scene);
                stage.setTitle("Dashboard");
            }else if(res.equals("password_error")){
                Msg.showError("Your password is incorrect.");
            }else if(res.equals("username_error")){
                Msg.showError("Your username is incorrect.");
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
    }
    
}
