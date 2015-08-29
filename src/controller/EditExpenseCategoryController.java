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
import model.ExpenseCategory;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class EditExpenseCategoryController implements Initializable {
    @FXML
    private ComboBox<ExpenseCategory> select_exp_cat;
    @FXML
    private TextField name;
    private List<ExpenseCategory> exp_list;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new AutoCompleteComboBoxListener<>(this.select_exp_cat);
        this.select_exp_cat.setOnHiding((e)->{
            ExpenseCategory a = this.select_exp_cat.getSelectionModel().getSelectedItem();
            this.select_exp_cat.setEditable(false);
            this.select_exp_cat.getSelectionModel().select(a);
        });
        this.select_exp_cat.setOnShowing((e)->{
            this.select_exp_cat.setEditable(true);
        });
        
        
        exp_list = FXCollections.observableArrayList();
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
            this.select_exp_cat.getItems().addAll(exp_list);
        }
    }    


    @FXML
    private void onUpdateButtonClick(ActionEvent event) {
        String name = this.name.getText();
        if(name.isEmpty()){
            Msg.showError("You must enter a category name.");
            return;
        }
        try {
            String res = Unirest.post(MetaData.baseUrl + "edit/expense-category")
                    .field("id", this.select_exp_cat.getSelectionModel().getSelectedItem().getId())
                    .field("name", this.name.getText())
                    .asString().getBody();
            if(res.equals("1")){
                Msg.showInformation("Expense Category has been updated successfully.");
            }else{
                Msg.showError("");
            }
            
        } catch (UnirestException ex) {
            Logger.getLogger(EditExpenseCategoryController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }finally{
            this.name.setText("");
            this.select_exp_cat.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void onExpenseCategorySelect(ActionEvent event) {
        String name = this.select_exp_cat.getSelectionModel().getSelectedItem().getName();
        this.name.setText(name);
    }
    
}
