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
import model.ExpenseReport;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class ExpenseReportController implements Initializable {
    @FXML
    private DatePicker start;
    @FXML
    private DatePicker end;
    @FXML
    private TableView<ExpenseReport> table;
    @FXML
    private TableColumn<ExpenseReport, Integer> sl;
    @FXML
    private TableColumn<ExpenseReport, Integer> id;
    @FXML
    private TableColumn<ExpenseReport, String> date;
    @FXML
    private TableColumn<ExpenseReport, String> category;
    @FXML
    private TableColumn<ExpenseReport, String> note;
    @FXML
    private TableColumn<ExpenseReport, Float> amount;
    private List<ExpenseReport> list;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.list = FXCollections.observableArrayList();
        sl.setCellValueFactory(new PropertyValueFactory("sl"));
        id.setCellValueFactory(new PropertyValueFactory("id"));
        date.setCellValueFactory(new PropertyValueFactory("date"));
        category.setCellValueFactory(new PropertyValueFactory("category"));
        note.setCellValueFactory(new PropertyValueFactory("note"));
        amount.setCellValueFactory(new PropertyValueFactory("amount"));
    }    

    @FXML
    private void onShowButtonClick(ActionEvent event) {
        this.table.getItems().clear();
        this.list.clear();
        String start = this.start.getValue().toString();
        String end = this.end.getValue().toString();
        
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "report/expense")
                    .queryString("start_date",start)
                    .queryString("end_date", end)
                    .asJson().getBody().getArray();
            for(int i=0; i< res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                int sl = obj.getInt("sl");
                int id = obj.getInt("entry_id");
                String date = obj.getString("date");
                String category = obj.getString("category");
                String note = obj.getString("description");
                float amount = Float.parseFloat(obj.getString("amount"));
                
                this.list.add(new ExpenseReport(sl, id, date, category, note, amount));
            }
        } catch (UnirestException ex) {
            Logger.getLogger(ExpenseReportController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }finally{
            this.table.getItems().addAll(list);
        }
    }
    
}
