/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import helpers.MetaData;
import helpers.Msg;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class ChangePasswordController implements Initializable {
    @FXML
    private PasswordField old_password;
    @FXML
    private PasswordField new_password;
    @FXML
    private PasswordField re_password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onSubmitButtonClick(ActionEvent event) {
        if(this.new_password.getText().isEmpty() || this.re_password.getText().isEmpty()){
            Msg.showError("");
            return;
        }
        if(this.old_password.getText().isEmpty() || !this.new_password.getText().equals(this.re_password.getText())){
            Msg.showError("");
            return;
        }else{
            try {
                String res = Unirest.post(MetaData.baseUrl + "change/password")
                        .field("old_password", this.old_password.getText())
                        .field("new_password", this.new_password.getText())
                        .asString().getBody();
                if(res.equals("1")){
                    Msg.showInformation("Your password is changed.");
                }else{
                    Msg.showError("");
                    return;
                }
            } catch (UnirestException ex) {
                Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
                Msg.showError("");
                return;
            }
        }
    }
    
}
