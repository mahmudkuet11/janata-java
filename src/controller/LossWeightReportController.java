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
import helpers.Report;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
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
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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

    @FXML
    private void onExportButtonClick(ActionEvent event) {
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
            Report report = new Report();
            Vector v = new Vector();
            HashMap params = new HashMap();
            v.addAll(list);
            try {
                start = new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(start));
                end = new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(end));
            } catch (ParseException ex) {
                Logger.getLogger(PurchaseReportController.class.getName()).log(Level.SEVERE, null, ex);
                Msg.showError("");
            }
            params.put("date", "From "+ start +" To " + end);
            report.getReport("src\\report\\LossReport.jrxml", new JRBeanCollectionDataSource(v), params);
        } catch (UnirestException ex) {
            Logger.getLogger(LossWeightReportController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
    }
    
}
