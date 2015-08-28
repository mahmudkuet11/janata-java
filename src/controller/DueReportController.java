/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mashape.unirest.http.Unirest;
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
import model.DueReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class DueReportController implements Initializable {
    @FXML
    private DatePicker start;
    @FXML
    private DatePicker end;
    @FXML
    private TableView<DueReport> table;
    @FXML
    private TableColumn<DueReport, Integer> sl;
    @FXML
    private TableColumn<DueReport, Integer> id;
    @FXML
    private TableColumn<DueReport, String> date;
    @FXML
    private TableColumn<DueReport, String> name;
    @FXML
    private TableColumn<DueReport, String> phone;
    @FXML
    private TableColumn<DueReport, String> address;
    @FXML
    private TableColumn<DueReport, String> item;
    @FXML
    private TableColumn<DueReport, Integer> caret;
    @FXML
    private TableColumn<DueReport, Integer> qty;
    @FXML
    private TableColumn<DueReport, Float> paid;
    @FXML
    private TableColumn<DueReport, Float> due;
    @FXML
    private TableColumn<DueReport, Float> loss;
    @FXML
    private TableColumn<DueReport, String> note;
    
    private List<DueReport> list;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = FXCollections.observableArrayList();
        sl.setCellValueFactory(new PropertyValueFactory("sl"));
        id.setCellValueFactory(new PropertyValueFactory("id"));
        date.setCellValueFactory(new PropertyValueFactory("date"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        phone.setCellValueFactory(new PropertyValueFactory("phone"));
        address.setCellValueFactory(new PropertyValueFactory("address"));
        item.setCellValueFactory(new PropertyValueFactory("item"));
        caret.setCellValueFactory(new PropertyValueFactory("caret"));
        qty.setCellValueFactory(new PropertyValueFactory("qty"));
        paid.setCellValueFactory(new PropertyValueFactory("paid"));
        due.setCellValueFactory(new PropertyValueFactory("due"));
        loss.setCellValueFactory(new PropertyValueFactory("loss"));
        note.setCellValueFactory(new PropertyValueFactory("note"));
    }    

    @FXML
    private void onShowButtonClick(ActionEvent event) {
        this.table.getItems().clear();
        this.list.clear();
        String start = this.start.getValue().toString();
        String end = this.end.getValue().toString();
        
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "report/due")
                    .queryString("start_date", start)
                    .queryString("end_date", end)
                    .asJson().getBody().getArray();
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                int sl = obj.getInt("sl");
                int id = obj.getInt("entry_id");
                String date = obj.getString("date");
                String name = obj.getString("customer_name");
                String phone = obj.getString("phone");
                String address = obj.getString("address");
                String item = obj.getString("category");
                int caret = obj.getInt("caret");
                int qty = obj.getInt("quantity");
                float paid = Float.parseFloat(obj.get("paid").toString());
                float due = Float.parseFloat(obj.get("due").toString());
                float loss = Float.parseFloat(obj.get("loss").toString());
                String note = obj.getString("note");
                this.list.add(new DueReport(sl, id, date, name, phone, address, item, caret, qty, paid, due, loss, note));
                
            }
        } catch (Exception ex) {
            Logger.getLogger(DueReportController.class.getName()).log(Level.SEVERE, null, ex);
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
            JSONArray res = Unirest.get(MetaData.baseUrl + "report/due")
                    .queryString("start_date", start)
                    .queryString("end_date", end)
                    .asJson().getBody().getArray();
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                int sl = obj.getInt("sl");
                int id = obj.getInt("entry_id");
                String date = obj.getString("date");
                String name = obj.getString("customer_name");
                String phone = obj.getString("phone");
                String address = obj.getString("address");
                String item = obj.getString("category");
                int caret = obj.getInt("caret");
                int qty = obj.getInt("quantity");
                float paid = Float.parseFloat(obj.get("paid").toString());
                float due = Float.parseFloat(obj.get("due").toString());
                float loss = Float.parseFloat(obj.get("loss").toString());
                String note = obj.getString("note");
                this.list.add(new DueReport(sl, id, date, name, phone, address, item, caret, qty, paid, due, loss, note));
                
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
            report.getReport("src\\report\\DueReport.jrxml", new JRBeanCollectionDataSource(v), params);
        } catch (Exception ex) {
            Logger.getLogger(DueReportController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
    }
    
}
