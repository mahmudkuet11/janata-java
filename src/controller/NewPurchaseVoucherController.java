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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Category;
import model.Supplier;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class NewPurchaseVoucherController implements Initializable {
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<Supplier> supplier;
    @FXML
    private TextArea note;
    @FXML
    private ComboBox<Category> category;
    @FXML
    private ComboBox<String> caret;
    @FXML
    private TextField quantity;
    @FXML
    private TextField p_rate;

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
                this.supplier.getItems().add(new Supplier(0,id,name,"",""));
            }
        } catch (UnirestException ex) {
            Logger.getLogger(NewPurchaseVoucherController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "get/category").asJson().getBody().getArray();
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                int id = obj.getInt("id");
                String name = obj.getString("name");
                float p_price = Float.parseFloat(obj.getString("purchase_price"));
                this.category.getItems().add(new Category(0,id,name,p_price,0,0,0));
            }
        } catch (UnirestException ex) {
            Logger.getLogger(NewPurchaseVoucherController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
        this.caret.getItems().addAll("18","21","22");
        
        new AutoCompleteComboBoxListener<>(this.supplier);
        this.supplier.setOnHiding((e)->{
            Supplier a = this.supplier.getSelectionModel().getSelectedItem();
            this.supplier.setEditable(false);
            this.supplier.getSelectionModel().select(a);
        });
        this.supplier.setOnShowing((e)->{
            this.supplier.setEditable(true);
        });
        
        new AutoCompleteComboBoxListener<>(this.category);
        this.category.setOnHiding((e)->{
            Category a = this.category.getSelectionModel().getSelectedItem();
            this.category.setEditable(false);
            this.category.getSelectionModel().select(a);
        });
        this.category.setOnShowing((e)->{
            this.category.setEditable(true);
        });
        
        
    }    

    @FXML
    private void onSaveButtonClick(ActionEvent event) {
        try{
            String date = this.date.getValue().toString();
            int supplier = this.supplier.getSelectionModel().getSelectedItem().getId();
            String note = this.note.getText();
            int category = this.category.getSelectionModel().getSelectedItem().getId();
            int caret = Integer.parseInt(this.caret.getSelectionModel().getSelectedItem());
            int quantity = Integer.parseInt(this.quantity.getText());
            float p_rate = Float.parseFloat(this.p_rate.getText());  
            JSONArray array = new JSONArray();
            
            JSONObject items_info = new JSONObject();
            items_info.put("category_id", category);
            items_info.put("caret", caret);
            items_info.put("quantity", quantity);
            items_info.put("purchase_rate", p_rate);
            
            array.put(items_info);
            System.out.println(array);
            String res = Unirest.post(MetaData.baseUrl + "add/purchase-voucher")
                    .field("date", date)
                    .field("supplier_id", supplier)
                    .field("note", note)
                    .field("items_info", array)
                    .asString().getBody();
            String res2 = Unirest.post(MetaData.baseUrl + "cash/withdraw")
                    .queryString("date",date)
                    .queryString("narration", "purchase "+ this.quantity.getText() +" piece "+ this.category.getSelectionModel().getSelectedItem().getName() +" from " + this.supplier.getSelectionModel().getSelectedItem().getName())
                    .queryString("amount", quantity * p_rate)
                    .asString().getBody();
            if(res.equals("1") && res2.equals("1")){
                Msg.showInformation("Purchase voucher has been added.");
            }else{
                Msg.showError("");
            }
        }catch(Exception ex){
            Logger.getLogger(NewPurchaseVoucherController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
    }

    @FXML
    private void onCategorySelect(ActionEvent event) {
        this.p_rate.setText(String.valueOf(this.category.getSelectionModel().getSelectedItem().getP_price()));
    }
    
}
