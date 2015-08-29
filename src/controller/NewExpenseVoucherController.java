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
import model.ExpenseCategory;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class NewExpenseVoucherController implements Initializable {
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<ExpenseCategory> category;
    @FXML
    private TextField amount;
    @FXML
    private TextArea note;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "get/expense-category").asJson().getBody().getArray();
            int sl = 1;
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                int id = obj.getInt("id");
                String name = obj.getString("name");
                
                this.category.getItems().add(new ExpenseCategory(sl, id, name));
                
                sl++;
            }
        } catch (UnirestException ex) {
            Logger.getLogger(NewExpenseVoucherController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }finally{
            new AutoCompleteComboBoxListener<>(this.category);
            this.category.setOnHiding((e)->{
                ExpenseCategory a = this.category.getSelectionModel().getSelectedItem();
                this.category.setEditable(false);
                this.category.getSelectionModel().select(a);
            });
            this.category.setOnShowing((e)->{
                this.category.setEditable(true);
            });
        }
    }    

    @FXML
    private void onSubmitButtonClick(ActionEvent event) {
        String date = this.date.getValue().toString();
        int cat_id = this.category.getSelectionModel().getSelectedItem().getId();
        float amount = Float.parseFloat(this.amount.getText());
        String note = this.note.getText();
        
        try {
            String res = Unirest.post(MetaData.baseUrl + "add/expense-voucher")
                    .field("date", date)
                    .field("category", cat_id)
                    .field("description", note)
                    .field("amount", amount)
                    .asString().getBody();
            String res2 = Unirest.post(MetaData.baseUrl + "cash/withdraw")
                    .queryString("date",date)
                    .queryString("narration", "Expense for " + this.category.getSelectionModel().getSelectedItem().getName())
                    .queryString("amount", amount)
                    .asString().getBody();
            if(res.equals("1") && res2.equals("1")){
                Msg.showInformation("Expense Voucher has been saved.");
            }else{
               Msg.showError(""); 
            }
        } catch (UnirestException ex) {
            Logger.getLogger(NewExpenseVoucherController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
    }
    
}
