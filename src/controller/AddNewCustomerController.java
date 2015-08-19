/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mashape.unirest.http.HttpResponse;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class AddNewCustomerController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private TextArea address;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onSaveButtonClick(ActionEvent event) {
        try {
            String name = this.name.getText();
            String phone = this.phone.getText();
            String address = this.address.getText();
            
            if(name.isEmpty() || phone.isEmpty() || address.isEmpty()){
                Msg.showError("You must enter name, phone and address.");
                return;
            }
            
            String res = Unirest.post(MetaData.baseUrl + "add/customer")
                    .field("name", name)
                    .field("phone", phone)
                    .field("address", address)
                    .asString().getBody();
            if(res.equals("1")){
                Msg.showInformation("Customer has been added successfully!!!");
                this.name.setText("");
                this.phone.setText("");
                this.address.setText("");
            }else{
                Msg.showError("");
            }
        } catch (UnirestException ex) {
            Logger.getLogger(AddNewCustomerController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
    }
    
}
