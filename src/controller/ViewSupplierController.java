/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import model.Supplier;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class ViewSupplierController implements Initializable {
    @FXML
    private TableView<Supplier> table;
    @FXML
    private TableColumn<Supplier, Integer> sl;
    @FXML
    private TableColumn<Supplier, String> name;
    @FXML
    private TableColumn<Supplier, String> phone;
    @FXML
    private TableColumn<Supplier, String> address;
    
    private List<Supplier> supplier_list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.supplier_list = FXCollections.observableArrayList();
        this.sl.setCellValueFactory(new PropertyValueFactory("sl"));
        this.name.setCellValueFactory(new PropertyValueFactory("name"));
        this.phone.setCellValueFactory(new PropertyValueFactory("phone"));
        this.address.setCellValueFactory(new PropertyValueFactory("address"));
        
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "get/supplier").asJson().getBody().getArray();
            int sl = 1;
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                String name = obj.getString("name");
                String phone = obj.getString("phone");
                String address = obj.getString("address");
                
                this.supplier_list.add(new Supplier(sl, name, phone, address));
                
                sl++;
                
            }
        } catch (UnirestException ex) {
            Logger.getLogger(ViewSupplierController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }finally{
            this.table.getItems().addAll(supplier_list);
        }
    }    
    
}
