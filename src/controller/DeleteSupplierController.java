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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Supplier;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class DeleteSupplierController implements Initializable {
    @FXML
    private ComboBox<Supplier> select_supplier;
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
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "get/supplier").asJson().getBody().getArray();
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                int id = obj.getInt("id");
                String name = obj.getString("name");
                String phone = obj.getString("phone");
                String address = obj.getString("address");
                
                this.select_supplier.getItems().add(new Supplier(0, id, name, phone, address));
            }
            
        } catch (UnirestException ex) {
            Logger.getLogger(EditSupplierController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
    }    

    @FXML
    private void onSupplierSelect(ActionEvent event) {
        this.name.setText(this.select_supplier.getSelectionModel().getSelectedItem().getName());
        this.phone.setText(this.select_supplier.getSelectionModel().getSelectedItem().getPhone());
        this.address.setText(this.select_supplier.getSelectionModel().getSelectedItem().getAddress());
    }

    @FXML
    private void onDeleteButtonClick(ActionEvent event) {
        int id = this.select_supplier.getSelectionModel().getSelectedItem().getId();
        try {
            String res = Unirest.post(MetaData.baseUrl + "delete/supplier").field("id", id).asString().getBody();
            if(res.equals("1")){
                Msg.showInformation("Supplier has been deleted successfully");
            }else{
                Msg.showError("");
            }
        } catch (UnirestException ex) {
            Logger.getLogger(DeleteSupplierController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
    }
    
}
