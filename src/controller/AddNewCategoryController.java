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
public class AddNewCategoryController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField purchase_price;
    @FXML
    private TextField sales_price;
    @FXML
    private TextField warning_quantity;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onAddNewCategoryClick(ActionEvent event) {
        String name = this.name.getText();
        String purchase_price = this.purchase_price.getText();
        String sales_price = this.sales_price.getText();
        String warning_quantity = this.warning_quantity.getText();
        if(name.isEmpty() || purchase_price.isEmpty() || sales_price.isEmpty() || warning_quantity.isEmpty()){
            Msg.showError("You must enter all informations.");
            return;
        }
        
        try {
            String res = Unirest.post(MetaData.baseUrl + "add/category")
                    .field("name", name)
                    .field("purchase_price", purchase_price)
                    .field("sell_price", sales_price)
                    .field("warning_quantity", warning_quantity)
                    .asString().getBody();
            if(res.equals("1")){
                Msg.showInformation("Category has been added successfully.");
            }else{
                Msg.showError("");
            }
            
        } catch (UnirestException ex) {
            Logger.getLogger(AddNewCategoryController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
    }
    
}
