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
import model.ExpenseCategory;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class ViewExpenseCategoryController implements Initializable {
    @FXML
    private TableView<ExpenseCategory> table;
    @FXML
    private TableColumn<ExpenseCategory, Integer> sl;
    @FXML
    private TableColumn<ExpenseCategory, Integer> id;
    @FXML
    private TableColumn<ExpenseCategory, String> name;
    
    private List<ExpenseCategory> exp_list;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exp_list = FXCollections.observableArrayList();
        sl.setCellValueFactory(new PropertyValueFactory("sl"));
        id.setCellValueFactory(new PropertyValueFactory("id"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "get/expense-category").asJson().getBody().getArray();
            int sl = 1;
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                int id = obj.getInt("id");
                String name = obj.getString("name");
                this.exp_list.add(new ExpenseCategory(sl, id, name));
                sl++;
            }
        } catch (UnirestException ex) {
            Logger.getLogger(ViewExpenseCategoryController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }finally{
            this.table.getItems().addAll(exp_list);
        }
        
    }    
    
}
