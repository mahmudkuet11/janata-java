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
import javafx.scene.control.Label;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class ProfitLossReportController implements Initializable {
    @FXML
    private Label sell;
    @FXML
    private Label paid;
    @FXML
    private Label due;
    @FXML
    private Label purchase;
    @FXML
    private Label expense;
    @FXML
    private Label profit;
    @FXML
    private DatePicker start;
    @FXML
    private DatePicker end;
    @FXML
    private Label profit_loss;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onShowButtonClick(ActionEvent event) {
        
        String start = this.start.getValue().toString();
        String end = this.end.getValue().toString();
        
        try {
            JSONObject res = Unirest.get(MetaData.baseUrl + "report/profit-loss")
                    .queryString("start_date", start)
                    .queryString("end_date", end)
                    .asJson().getBody().getObject();
            this.paid.setText(res.getString("total_paid"));
            this.due.setText(res.getString("total_due"));
            this.sell.setText(res.getString("total_sell"));
            this.purchase.setText(res.getString("total_purchase"));
            this.expense.setText(res.getString("total_expense"));
            
            float sell = 0, purchase = 0, expense = 0, profit = 0;
            sell = Float.parseFloat(res.getString("total_sell"));
            purchase = Float.parseFloat(res.getString("total_purchase"));
            expense = Float.parseFloat(res.getString("total_expense"));
            profit = sell - purchase - expense;
            if(profit < 0){
                this.profit_loss.setText("Loss");
                this.profit.setText(String.valueOf(profit * (-1)));
            }else{
                this.profit_loss.setText("Profit");
                this.profit.setText(String.valueOf(profit));
            }
        } catch (UnirestException ex) {
            Logger.getLogger(ProfitLossReportController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
    }
    
}
