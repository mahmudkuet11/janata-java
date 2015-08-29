/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import helpers.AutoCompleteComboBoxListener;
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
import model.Customer;
import model.Supplier;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class EditSupplierController implements Initializable {
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
        
        new AutoCompleteComboBoxListener<>(this.select_supplier);
        this.select_supplier.setOnHiding((e)->{
            Supplier a = this.select_supplier.getSelectionModel().getSelectedItem();
            this.select_supplier.setEditable(false);
            this.select_supplier.getSelectionModel().select(a);
        });
        this.select_supplier.setOnShowing((e)->{
            this.select_supplier.setEditable(true);
        });
    }    


    @FXML
    private void onUpdateButtonClick(ActionEvent event) {
        String name = this.name.getText();
        String phone = this.phone.getText();
        String address = this.address.getText();
        
        try {
            String res = Unirest.post(MetaData.baseUrl + "edit/supplier")
                    .field("id", this.select_supplier.getSelectionModel().getSelectedItem().getId())
                    .field("name", name)
                    .field("phone", phone)
                    .field("address", address)
                    .asString().getBody();
            if(res.equals("1")){
                Msg.showInformation("Supplier has been updated successfully.");
            }else{
                Msg.showError("");
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
    
}
