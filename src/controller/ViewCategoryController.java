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
import model.Category;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class ViewCategoryController implements Initializable {
    @FXML
    private TableView<Category> table;
    @FXML
    private TableColumn<Category, Integer> sl;
    @FXML
    private TableColumn<Category, String> name;
    @FXML
    private TableColumn<Category, Float> p_price;
    @FXML
    private TableColumn<Category, Float> s_price;
    @FXML
    private TableColumn<Category, Integer> warning_qty;
    @FXML
    private TableColumn<Category, Integer> current_qty;
    
    private List<Category> category_list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        category_list = FXCollections.observableArrayList();
        
        sl.setCellValueFactory(new PropertyValueFactory("sl"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        p_price.setCellValueFactory(new PropertyValueFactory("p_price"));
        s_price.setCellValueFactory(new PropertyValueFactory("s_price"));
        warning_qty.setCellValueFactory(new PropertyValueFactory("warning_qty"));
        current_qty.setCellValueFactory(new PropertyValueFactory("current_qty"));
        
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "get/category").asJson().getBody().getArray();
            int sl = 1;
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                String name = obj.getString("name");
                float p_price = Float.parseFloat(obj.get("purchase_price").toString());
                float s_price = Float.parseFloat(obj.get("sell_price").toString());
                int warning_qty = obj.getInt("warning_quantity");
                int current_qty = obj.getInt("current_quantity");
                
                this.category_list.add(new Category(sl, 0, name, p_price, s_price, warning_qty, current_qty));
                
                sl++;
            }
        } catch (UnirestException ex) {
            Logger.getLogger(ViewCategoryController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }finally{
            this.table.getItems().addAll(this.category_list);
        }
        
    }    
    
}
