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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class AddNewExpenseCategoryController implements Initializable {
    @FXML
    private TextField name;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onAddExpenseCategoryClick(ActionEvent event) {
        String name = this.name.getText();
        if(name.isEmpty()){
            Msg.showError("You must enter a category name.");
            return;
        }
        try {
            String res = Unirest.post(MetaData.baseUrl + "add/expense-category").field("name", name).asString().getBody();
            if(res.equals("1")){
                Msg.showInformation("Expense Category has been added successfully.");
            }else{
                Msg.showError("");
            }
        } catch (UnirestException ex) {
            Logger.getLogger(AddNewExpenseCategoryController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }finally{
            this.name.setText("");
        }
    }
    
}
