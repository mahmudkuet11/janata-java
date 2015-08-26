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
import model.LossReport;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class LossWeightReportController implements Initializable {
    @FXML
    private DatePicker start;
    @FXML
    private DatePicker end;
    @FXML
    private TableView<LossReport> table;
    @FXML
    private TableColumn<LossReport, Integer> sl;
    @FXML
    private TableColumn<LossReport, String> date;
    @FXML
    private TableColumn<LossReport, String> category;
    @FXML
    private TableColumn<LossReport, Integer> caret;
    @FXML
    private TableColumn<LossReport, Float> loss;
    
    private List<LossReport> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = FXCollections.observableArrayList();
        sl.setCellValueFactory(new PropertyValueFactory("sl"));
        date.setCellValueFactory(new PropertyValueFactory("date"));
        category.setCellValueFactory(new PropertyValueFactory("category"));
        caret.setCellValueFactory(new PropertyValueFactory("caret"));
        loss.setCellValueFactory(new PropertyValueFactory("loss"));
        
    }    

    @FXML
    private void onShowButtonClick(ActionEvent event) {
        this.table.getItems().clear();
        this.list.clear();
        String start = this.start.getValue().toString();
        String end = this.end.getValue().toString();
        
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl +  "report/weight-loss")
                    .queryString("start_date", start)
                    .queryString("end_date", end)
                    .asJson().getBody().getArray();
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                int sl = obj.getInt("sl");
                String date = obj.getString("date");
                String category = obj.getString("category");
                int caret = obj.getInt("caret");
                float loss = Float.parseFloat(obj.get("loss").toString());
                
                this.list.add(new LossReport(sl, date, category, caret, loss));
            }
        } catch (UnirestException ex) {
            Logger.getLogger(LossWeightReportController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }finally{
            this.table.getItems().addAll(list);
        }
    }
    
}
