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
import java.io.IOException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class DeleteCustomerController implements Initializable {
    @FXML
    private ComboBox<Customer> select_customer;
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private TextArea address;
    @FXML
    private Button delete;

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
            Msg.showError("Sorry, we could not fetch data from server. Check your connections and try agagin.");
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
    private void onDeleteButtonCLick(ActionEvent event) throws IOException {
        try {
            String res = Unirest.post(MetaData.baseUrl + "delete/customer").field("id", this.select_customer.getSelectionModel().getSelectedItem().getId()).asString().getBody();
            if(res.equals("1")){
                Msg.showInformation("Customer has been deleted successfully.");
            }else{
                Msg.showError("");
            }
        } catch (UnirestException ex) {
            Logger.getLogger(DeleteCustomerController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }finally{
            Parent root = FXMLLoader.load(getClass().getResource("/view/DeleteCustomer.fxml"));
            Scene scene = this.address.getScene();
            Stage stage = (Stage)this.address.getScene().getWindow();
            scene.setRoot(root);
            stage.setScene(scene);
            stage.setTitle("Delete Customer");
        }
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
    
}
