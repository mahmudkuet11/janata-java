/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.SalesReport;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class CustomerWiseSalesReportController implements Initializable {
    @FXML
    private DatePicker start;
    @FXML
    private DatePicker end;
    @FXML
    private TableColumn<SalesReport, Integer> sl;
    @FXML
    private TableColumn<SalesReport, Integer> id;
    @FXML
    private TableColumn<SalesReport, String> date;
    @FXML
    private TableColumn<SalesReport, String> category;
    @FXML
    private TableColumn<SalesReport, Integer> caret;
    @FXML
    private TableColumn<SalesReport, Integer> qty;
    @FXML
    private TableColumn<SalesReport, Float> rate;
    @FXML
    private TableColumn<SalesReport, Float> total;
    @FXML
    private TableColumn<SalesReport, Float> paid;
    @FXML
    private TableColumn<SalesReport, Float> due;
    @FXML
    private TableColumn<SalesReport, Float> loss;
    @FXML
    private TableColumn<SalesReport, String> note;
    @FXML
    private TableView<SalesReport> table;
    
    private List<SalesReport> list;
    @FXML
    private ComboBox<Customer> customer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = FXCollections.observableArrayList();
        
        sl.setCellValueFactory(new PropertyValueFactory("sl"));
        id.setCellValueFactory(new PropertyValueFactory("id"));
        date.setCellValueFactory(new PropertyValueFactory("date"));
        category.setCellValueFactory(new PropertyValueFactory("category"));
        caret.setCellValueFactory(new PropertyValueFactory("caret"));
        qty.setCellValueFactory(new PropertyValueFactory("qty"));
        rate.setCellValueFactory(new PropertyValueFactory("rate"));
        total.setCellValueFactory(new PropertyValueFactory("total"));
        paid.setCellValueFactory(new PropertyValueFactory("paid"));
        due.setCellValueFactory(new PropertyValueFactory("due"));
        loss.setCellValueFactory(new PropertyValueFactory("loss"));
        note.setCellValueFactory(new PropertyValueFactory("note"));
        
        try {
            HttpResponse<JsonNode> res = Unirest.get(MetaData.baseUrl + "get/customer").asJson();
            JSONArray array = res.getBody().getArray();
            for(int i=0; i<array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                int id = obj.getInt("id");
                String name = obj.getString("name");
                String phone = obj.getString("phone");
                String address = obj.getString("address");
                
                this.customer.getItems().add(new Customer(0, id, name, phone, address, 1));
            }
            
        } catch (UnirestException ex) {
            Logger.getLogger(EditCustomerController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
    }    

    @FXML
    private void onShowButtonClick(ActionEvent event) {
        this.table.getItems().clear();
        this.list.clear();
        String start = this.start.getValue().toString();
        String end = this.end.getValue().toString();
        if(this.customer.getSelectionModel().isEmpty()){
            Msg.showError("You must select a customer.");
            return;
        }
        int customer_id = this.customer.getSelectionModel().getSelectedItem().getId();
        
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "report/sell/" + customer_id)
                    .queryString("start_date", start)
                    .queryString("end_date", end)
                    .asJson().getBody().getArray();
            
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                
                int sl = obj.getInt("sl");
                int id = obj.getInt("entry_id");
                String date = obj.getString("date");
                String category = obj.getString("category");
                int caret = obj.getInt("caret");
                int qty = obj.getInt("quantity");
                float rate = Float.parseFloat(obj.get("sales_rate").toString());
                float total = Float.parseFloat(obj.get("total_amount").toString());
                float paid = Float.parseFloat(obj.get("paid").toString());
                float due = Float.parseFloat(obj.get("due").toString());
                float loss = Float.parseFloat(obj.get("loss").toString());
                String note = obj.getString("note");
                
                this.list.add(new SalesReport(sl, id, date, category, caret, "", qty, rate, total, paid, due, loss, note));
                
            }
        } catch (UnirestException ex) {
            Logger.getLogger(SalesReportController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }finally{
            this.table.getItems().addAll(list);
        }
    }
    
}
