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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CashReport;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class CashReportController implements Initializable {
    @FXML
    private DatePicker start;
    @FXML
    private DatePicker end;
    @FXML
    private TableView<CashReport> table;
    @FXML
    private TableColumn<CashReport, Integer> sl;
    @FXML
    private TableColumn<CashReport, String> date;
    @FXML
    private TableColumn<CashReport, String> narration;
    @FXML
    private TableColumn<CashReport, Float> amount;
    @FXML
    private TableColumn<CashReport, Float> balance;
    
    private List<CashReport> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = FXCollections.observableArrayList();
        
        sl.setCellValueFactory(new PropertyValueFactory("sl"));
        date.setCellValueFactory(new PropertyValueFactory("date"));
        narration.setCellValueFactory(new PropertyValueFactory("narration"));
        amount.setCellValueFactory(new PropertyValueFactory("amount"));
        balance.setCellValueFactory(new PropertyValueFactory("balance"));
    }    

    @FXML
    private void onShowReportClick(ActionEvent event) {
        String start_date = this.start.getValue().toString();
        String end_date = this.end.getValue().toString();
        
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "report/cash")
                    .queryString("start_date", start_date)
                    .queryString("end_date", end_date)
                    .asJson().getBody().getArray();
            int sl = 1;
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                String date = obj.getString("date");
                String narration = obj.getString("narration");
                float amount = Float.parseFloat(obj.get("amount").toString());
                float balance = Float.parseFloat(obj.get("balance").toString());
                this.list.add(new CashReport(sl, date, narration, amount, balance));
                sl++;
            }
            
        } catch (UnirestException ex) {
            Logger.getLogger(CashReportController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }finally{
            this.table.getItems().addAll(list);
        }
    }
    
}
