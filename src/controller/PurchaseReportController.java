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
import model.PurchaseReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class PurchaseReportController implements Initializable {
    @FXML
    private DatePicker start;
    @FXML
    private DatePicker end;
    @FXML
    private TableView<PurchaseReport> table;
    @FXML
    private TableColumn<PurchaseReport, Integer> sl;
    @FXML
    private TableColumn<PurchaseReport, Integer> id;
    @FXML
    private TableColumn<PurchaseReport, String> date;
    @FXML
    private TableColumn<PurchaseReport, String> category;
    @FXML
    private TableColumn<PurchaseReport, Integer> caret;
    @FXML
    private TableColumn<PurchaseReport, String> supplier;
    @FXML
    private TableColumn<PurchaseReport, Integer> quantity;
    @FXML
    private TableColumn<PurchaseReport, Float> rate;
    @FXML
    private TableColumn<PurchaseReport, Float> amount;
    @FXML
    private TableColumn<PurchaseReport, String> note;
    
    private List<PurchaseReport> list;
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
        caret.setCellValueFactory(new PropertyValueFactory("caret"));
        supplier.setCellValueFactory(new PropertyValueFactory("supplier"));
        quantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        rate.setCellValueFactory(new PropertyValueFactory("rate"));
        amount.setCellValueFactory(new PropertyValueFactory("amount"));
        note.setCellValueFactory(new PropertyValueFactory("note"));
        
    }    

    @FXML
    private void onShowReportClick(ActionEvent event) {
        this.table.getItems().clear();
        this.list.clear();
        
        String start_date = this.start.getValue().toString();
        String end_date = this.end.getValue().toString();
        
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "report/purchase")
                    .queryString("start_date",start_date)
                    .queryString("end_date",end_date)
                    .asJson().getBody().getArray();
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                
                int sl = obj.getInt("sl");
                int id = obj.getInt("entry_id");
                String date = obj.getString("date");
                String category = obj.getString("category");
                int caret = obj.getInt("caret");
                String supplier = obj.getString("supplier");
                int quantity = obj.getInt("quantity");
                float rate = Float.parseFloat(obj.get("purchase_rate").toString());
                float amount = Float.parseFloat(obj.get("total_amount").toString());
                String note = obj.getString("note");
                
                this.list.add(new PurchaseReport(sl, id, date, category, caret, supplier, quantity, rate, amount, note));
            }
            
        } catch (UnirestException ex) {
            Logger.getLogger(PurchaseReportController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }finally{
            this.table.getItems().addAll(list);
        }
    }

    @FXML
    private void onExportReportClick(ActionEvent event) {
        this.list.clear();
        
        String start_date = this.start.getValue().toString();
        String end_date = this.end.getValue().toString();
        
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "report/purchase")
                    .queryString("start_date",start_date)
                    .queryString("end_date",end_date)
                    .asJson().getBody().getArray();
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                
                int sl = obj.getInt("sl");
                int id = obj.getInt("entry_id");
                String date = obj.getString("date");
                String category = obj.getString("category");
                int caret = obj.getInt("caret");
                String supplier = obj.getString("supplier");
                int quantity = obj.getInt("quantity");
                float rate = Float.parseFloat(obj.get("purchase_rate").toString());
                float amount = Float.parseFloat(obj.get("total_amount").toString());
                String note = obj.getString("note");
                
                this.list.add(new PurchaseReport(sl, id, date, category, caret, supplier, quantity, rate, amount, note));
            }
            
        } catch (UnirestException ex) {
            Logger.getLogger(PurchaseReportController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
        Report report = new Report();
        Vector v = new Vector();
        HashMap params = new HashMap();
        v.addAll(list);
        try {
            start_date = new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(start_date));
            end_date = new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(end_date));
        } catch (ParseException ex) {
            Logger.getLogger(PurchaseReportController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
        params.put("date", "From "+ start_date +" To " + end_date);
        report.getReport("src\\report\\PurchaseReport.jrxml", new JRBeanCollectionDataSource(v), params);
    }
    
}
