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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class CashDepositController implements Initializable {
    @FXML
    private DatePicker date;
    @FXML
    private TextField amount;
    @FXML
    private TextArea note;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onDepositButtonClick(ActionEvent event) {
        String date = this.date.getValue().toString();
        Float amount = Float.parseFloat(this.amount.getText());
        
        try {
            String res = Unirest.post(MetaData.baseUrl + "cash/deposit")
                    .field("date", date)
                    .field("narration", this.note.getText())
                    .field("amount", amount)
                    .asString().getBody();
            if(res.equals("1")){
                Msg.showInformation("success");
            }else{
                Msg.showError("");
            }
            
        } catch (UnirestException ex) {
            Logger.getLogger(CashDepositController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }finally{
            this.amount.setText("");
            this.note.setText("");
            this.date.setValue(null);
        }
    }
    
}
