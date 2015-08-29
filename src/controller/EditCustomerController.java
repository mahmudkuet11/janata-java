/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class EditCustomerController implements Initializable {
    @FXML
    private ComboBox<Customer> select_customer;
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
            HttpResponse<JsonNode> res = Unirest.get(MetaData.baseUrl + "get/customer").asJson();
            JSONArray array = res.getBody().getArray();
            for(int i=0; i<array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                int id = obj.getInt("id");
                String name = obj.getString("name");
                String phone = obj.getString("phone");
                String address = obj.getString("address");
                
                this.select_customer.getItems().add(new Customer(0, id, name, phone, address, 1));
            }
            
        } catch (UnirestException ex) {
            Logger.getLogger(EditCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new AutoCompleteComboBoxListener<>(this.select_customer);
        this.select_customer.setOnHiding((e)->{
            Customer a = this.select_customer.getSelectionModel().getSelectedItem();
            this.select_customer.setEditable(false);
            this.select_customer.getSelectionModel().select(a);
        });
        this.select_customer.setOnShowing((e)->{
            this.select_customer.setEditable(true);
        });
    }    

    @FXML
    private void onCustomerSelect(ActionEvent event) {
        if(this.select_customer.getSelectionModel().isEmpty()){
            return;
        }
        this.name.setText(this.select_customer.getSelectionModel().getSelectedItem().getName());
        this.phone.setText(this.select_customer.getSelectionModel().getSelectedItem().getPhone());
        this.address.setText(this.select_customer.getSelectionModel().getSelectedItem().getAddress());
    }

    @FXML
    private void onUpdateButtonClick(ActionEvent event) {
        String name = this.name.getText();
        String phone = this.phone.getText();
        String address = this.address.getText();
        
        if(name.isEmpty() || phone.isEmpty() || address.isEmpty()){
            Msg.showError("You must enter name, phone and address.");
            return;
        }
        
        try {
            String res = Unirest.post(MetaData.baseUrl + "edit/customer")
                    .field("id", this.select_customer.getSelectionModel().getSelectedItem().getId())
                    .field("name", name)
                    .field("phone", phone)
                    .field("address", address)
                    .asString().getBody();
            if(res.equals("1")){
                Msg.showInformation("Customer has been updated successfully!!!");
                this.name.setText("");
                this.phone.setText("");
                this.address.setText("");
                this.select_customer.getSelectionModel().clearSelection();
            }
        } catch (UnirestException ex) {
            Logger.getLogger(EditCustomerController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("Unable to retrive data from server!!!");
        }
    }
    
}
