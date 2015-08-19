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
import helpers.MetaData;
import helpers.Msg;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class ViewCustomerController implements Initializable {
    @FXML
    private TableView<Customer> table;
    @FXML
    private TableColumn<Customer, Integer> sl;
    @FXML
    private TableColumn<Customer, String> name;
    @FXML
    private TableColumn<Customer, String> phone;
    @FXML
    private TableColumn<Customer, String> address;
    
    private List<Customer> customers;
    @FXML
    private TableColumn<Customer, Integer> id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customers = FXCollections.observableArrayList();
        
        sl.setCellValueFactory(new PropertyValueFactory("sl"));
        id.setCellValueFactory(new PropertyValueFactory("id"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        phone.setCellValueFactory(new PropertyValueFactory("phone"));
        address.setCellValueFactory(new PropertyValueFactory("address"));
        
        new Thread(()->{
            try {
                HttpResponse<JsonNode> res = Unirest.get(MetaData.baseUrl + "get/customer").asJson();
                JSONArray array = res.getBody().getArray();
                int sl = 1;
                for(int i=0; i<array.length(); i++){
                    JSONObject obj = array.getJSONObject(i);
                    int id = obj.getInt("id");
                    String name = obj.getString("name");
                    String phone = obj.getString("phone");
                    String address = obj.getString("address");
                    int active = obj.getInt("active");
                    
                    customers.add(new Customer(sl, id, name, phone, address, active));
                    sl++;
                }
                this.table.getItems().addAll(customers);
            } catch (UnirestException ex) {
                Logger.getLogger(ViewCustomerController.class.getName()).log(Level.SEVERE, null, ex);
                Msg.showError("Unable to retrive data from server!!!");
            }
        }).run();
    }    
    
}
