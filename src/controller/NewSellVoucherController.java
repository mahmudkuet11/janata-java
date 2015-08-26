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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Category;
import model.Customer;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class NewSellVoucherController implements Initializable {
    @FXML
    private DatePicker date;
    @FXML
    private DatePicker delivery_date;
    @FXML
    private ComboBox<Customer> customer;
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private TextArea address;
    @FXML
    private ComboBox<Category> item;
    @FXML
    private ComboBox<String> caret;
    @FXML
    private TextField quantity;
    @FXML
    private TextField rate;
    @FXML
    private TextField paid;
    @FXML
    private TextField due;
    @FXML
    private TextField total;
    @FXML
    private TextField total_weight;
    @FXML
    private TextField loss;
    @FXML
    private TextField weight_without_loss;
    @FXML
    private TextField seal;
    @FXML
    private Button submit;
    @FXML
    private TextArea note;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        * Customer init
        */
        try {
            HttpResponse<JsonNode> res = Unirest.get(MetaData.baseUrl + "get/customer").asJson();
            JSONArray array = res.getBody().getArray();
            for(int i=0; i<array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                int id = obj.getInt("id");
                String name = obj.getString("name");
                String phone = obj.getString("phone");
                String address = obj.getString("address");
                
                this.customer.getItems().add(new Customer(0, id, name, phone, address, 1));
            }
            
        } catch (UnirestException ex) {
            Logger.getLogger(EditCustomerController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
        /*
        * Category init
        */
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "get/category").asJson().getBody().getArray();
            int sl = 1;
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                int id = obj.getInt("id");
                String name = obj.getString("name");
                float p_price = Float.parseFloat(obj.get("purchase_price").toString());
                float s_price = Float.parseFloat(obj.get("sell_price").toString());
                int warning_qty = obj.getInt("warning_quantity");
                int current_qty = obj.getInt("current_quantity");
                
                this.item.getItems().add(new Category(sl, id, name, p_price, s_price, warning_qty, current_qty));
                
                sl++;
            }
        } catch (UnirestException ex) {
            Logger.getLogger(EditCategoryController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
        /*
        *   caret init
        */
        this.caret.getItems().addAll("18","21","22");
    }    

    @FXML
    private void onCustomerSelect(ActionEvent event) {
        if(this.customer.getSelectionModel().isEmpty()){
            return;
        }
        this.name.setText(this.customer.getSelectionModel().getSelectedItem().getName());
        this.phone.setText(this.customer.getSelectionModel().getSelectedItem().getPhone());
        this.address.setText(this.customer.getSelectionModel().getSelectedItem().getAddress());
    }

    @FXML
    private void onSubmitButtonClick(ActionEvent event) {
        /*
        *   to add sell voucher
        */
        String date = this.date.getValue().toString();
        int customer_id = this.customer.getSelectionModel().getSelectedItem().getId();
        String note = this.note.getText();
        int category_id = this.item.getSelectionModel().getSelectedItem().getId();
        int caret = Integer.parseInt(this.caret.getSelectionModel().getSelectedItem());
        int quantity = Integer.parseInt(this.quantity.getText());
        float rate = Float.parseFloat(this.rate.getText());
        float paid = Float.parseFloat(this.paid.getText());
        float due = Float.parseFloat(this.due.getText());
        float loss = Float.parseFloat(this.loss.getText());
        
        JSONObject obj = new JSONObject();
        obj.put("date", date);
        obj.put("category_id", category_id);
        obj.put("caret", caret);
        obj.put("quantity", quantity);
        obj.put("sell_rate", rate);
        obj.put("customer_id", customer_id);
        obj.put("paid_amount", paid);
        obj.put("due", due);
        obj.put("note", note);
        obj.put("loss", loss);
        
        JSONArray array = new JSONArray();
        array.put(obj);
        System.out.println(array);
        try {
            String res = Unirest.post(MetaData.baseUrl + "add/sell-voucher")
                    .field("date", date)
                    .field("customer_id", customer_id)
                    .field("note", note)
                    .field("items_info", array)
                    .asString().getBody();
            System.out.println(res);
            if(res.equals("1")){
                Msg.showInformation("Sell Voucher hass been added successfully.");
            }else{
                Msg.showError("");
            }
        } catch (UnirestException ex) {
            Logger.getLogger(NewSellVoucherController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
    }
    
}