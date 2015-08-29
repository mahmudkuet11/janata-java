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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class BankWithdrawController implements Initializable {
    @FXML
    private DatePicker date;
    @FXML
    private TextField amount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onWithdrawClick(ActionEvent event) {
        String date = this.date.getValue().toString();
        float amount = Float.parseFloat(this.amount.getText());
        
        try {
            String res = Unirest.post(MetaData.baseUrl + "bank/withdraw")
                    .field("date", date)
                    .field("narration", "Withdraw from bank and deposited on cash account")
                    .field("amount", amount)
                    .asString().getBody();
            String res2 = Unirest.post(MetaData.baseUrl + "cash/deposit")
                    .queryString("date",date)
                    .queryString("narration", "Withdraw from bank and deposited on cash account")
                    .queryString("amount", amount)
                    .asString().getBody();
            if(res.equals("1") && res2.equals("1")){
                Msg.showInformation("Withdraw successfull and deposited on cash account");
            }
        } catch (UnirestException ex) {
            Logger.getLogger(BankWithdrawController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
    }
    
}
