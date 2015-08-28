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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.PurchaseReport;
import model.Supplier;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class SupplierWisePurchaseReportController implements Initializable {
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
    private TableColumn<PurchaseReport, Integer> quantity;
    @FXML
    private TableColumn<PurchaseReport, Float> rate;
    @FXML
    private TableColumn<PurchaseReport, Float> amount;
    @FXML
    private TableColumn<PurchaseReport, String> note;
    
    private List<PurchaseReport> list;
    @FXML
    private ComboBox<Supplier> supplier;

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
        quantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        rate.setCellValueFactory(new PropertyValueFactory("rate"));
        amount.setCellValueFactory(new PropertyValueFactory("amount"));
        note.setCellValueFactory(new PropertyValueFactory("note"));
        
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "get/supplier").asJson().getBody().getArray();
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                int id = obj.getInt("id");
                String name = obj.getString("name");
                String phone = obj.getString("phone");
                String address = obj.getString("address");
                
                this.supplier.getItems().add(new Supplier(0, id, name, phone, address));
            }
            
        } catch (UnirestException ex) {
            Logger.getLogger(EditSupplierController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
    }    

    @FXML
    private void onShowReportClick(ActionEvent event) {
        this.table.getItems().clear();
        this.list.clear();
        String start_date = this.start.getValue().toString();
        String end_date = this.end.getValue().toString();
        if(this.supplier.getSelectionModel().isEmpty()){
            Msg.showError("You must select a supplier.");
            return;
        }
        int supplier_id = this.supplier.getSelectionModel().getSelectedItem().getId();
        
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "report/purchase/" + supplier_id)
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
                int quantity = obj.getInt("quantity");
                float rate = Float.parseFloat(obj.get("purchase_rate").toString());
                float amount = Float.parseFloat(obj.get("total_amount").toString());
                String note = obj.getString("note");
                
                this.list.add(new PurchaseReport(sl, id, date, category, caret, "", quantity, rate, amount, note));
            }
            
        } catch (UnirestException ex) {
            Logger.getLogger(PurchaseReportController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }finally{
            this.table.getItems().addAll(list);
        }
    }

    @FXML
    private void onExportRepoprtClick(ActionEvent event) {
        this.list.clear();
        String start_date = this.start.getValue().toString();
        String end_date = this.end.getValue().toString();
        if(this.supplier.getSelectionModel().isEmpty()){
            Msg.showError("You must select a supplier.");
            return;
        }
        int supplier_id = this.supplier.getSelectionModel().getSelectedItem().getId();
        
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "report/purchase/" + supplier_id)
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
                int quantity = obj.getInt("quantity");
                float rate = Float.parseFloat(obj.get("purchase_rate").toString());
                float amount = Float.parseFloat(obj.get("total_amount").toString());
                String note = obj.getString("note");
                
                this.list.add(new PurchaseReport(sl, id, date, category, caret, "", quantity, rate, amount, note));
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
            params.put("supplier", this.supplier.getSelectionModel().getSelectedItem().getName());
            report.getReport("src\\report\\SupplierWisePurchaseReport.jrxml", new JRBeanCollectionDataSource(v), params);

            } catch (UnirestException ex) {
                Logger.getLogger(PurchaseReportController.class.getName()).log(Level.SEVERE, null, ex);
                Msg.showError("");
            }
    }
    
}
