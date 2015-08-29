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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class AddNewSupplierController implements Initializable {
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
        String name = this.name.getText();
        String phone = this.phone.getText();
        String address = this.address.getText();
        
        try {
            String res = Unirest.post(MetaData.baseUrl + "add/supplier")
                    .field("name", name)
                    .field("phone", phone)
                    .field("address", address)
                    .asString().getBody();
            if(res.equals("1")){
                Msg.showInformation("Supplier has been added successfully.");
            }else{
                Msg.showError("");
            }
        } catch (UnirestException ex) {
            Logger.getLogger(AddNewSupplierController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }finally{
            this.name.setText("");
            this.phone.setText("");
            this.address.setText("");
        }
    }
    
}
