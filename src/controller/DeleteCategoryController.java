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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Category;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class DeleteCategoryController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField p_price;
    @FXML
    private TextField s_price;
    @FXML
    private TextField warning_qty;
    @FXML
    private ComboBox<Category> select_category;
    private List<Category> category_list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new AutoCompleteComboBoxListener<>(this.select_category);
        this.select_category.setOnHiding((e)->{
            Category a = this.select_category.getSelectionModel().getSelectedItem();
            this.select_category.setEditable(false);
            this.select_category.getSelectionModel().select(a);
        });
        this.select_category.setOnShowing((e)->{
            this.select_category.setEditable(true);
        });
        
        
        category_list = FXCollections.observableArrayList();
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "get/category").asJson().getBody().getArray();
            int sl = 1;
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                String name = obj.getString("name");
                int id = obj.getInt("id");
                float p_price = Float.parseFloat(obj.get("purchase_price").toString());
                float s_price = Float.parseFloat(obj.get("sell_price").toString());
                int warning_qty = obj.getInt("warning_quantity");
                int current_qty = obj.getInt("current_quantity");
                
                this.category_list.add(new Category(sl, id, name, p_price, s_price, warning_qty, current_qty));
                
                sl++;
            }
        } catch (UnirestException ex) {
            Logger.getLogger(EditCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.select_category.getItems().addAll(this.category_list);
        }
    }    

    @FXML
    private void onDeleteButtonClick(ActionEvent event) {
        int id = this.select_category.getSelectionModel().getSelectedItem().getId();
        try {
            String res = Unirest.post(MetaData.baseUrl + "delete/category").field("id", id).asString().getBody();
            if(res.equals("1")){
                Msg.showInformation("Category has been deleted.");
            }else{
                Msg.showError("");
            }
        } catch (UnirestException ex) {
            Logger.getLogger(DeleteCategoryController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
    }

    @FXML
    private void onSelectCategory(ActionEvent event) {
        String name = this.select_category.getSelectionModel().getSelectedItem().getName();
        float p_price = this.select_category.getSelectionModel().getSelectedItem().getP_price();
        float s_price = this.select_category.getSelectionModel().getSelectedItem().getS_price();
        int warning_qty = this.select_category.getSelectionModel().getSelectedItem().getWarning_qty();
        
        this.name.setText(name);
        this.p_price.setText(String.valueOf(p_price));
        this.s_price.setText(String.valueOf(s_price));
        this.warning_qty.setText(String.valueOf(warning_qty));
    }
    
}
