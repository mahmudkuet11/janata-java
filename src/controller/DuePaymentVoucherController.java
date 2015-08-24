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
import model.DuePayment;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Customer;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class DuePaymentVoucherController implements Initializable {
    @FXML
    private TableView<DuePayment> table;
    @FXML
    private TableColumn<DuePayment, Integer> sl;
    @FXML
    private TableColumn<DuePayment, String> date;
    @FXML
    private TableColumn<DuePayment, String> item;
    @FXML
    private TableColumn<DuePayment, Integer> caret;
    @FXML
    private TableColumn<DuePayment, Integer> quantity;
    @FXML
    private TableColumn<DuePayment, Float> rate;
    @FXML
    private TableColumn<DuePayment, Float> paid;
    @FXML
    private TableColumn<DuePayment, Float> due;
    @FXML
    private TableColumn<DuePayment, Float> loss;
    @FXML
    private TableColumn<DuePayment, String> note;
    
    private List<DuePayment> list;
    @FXML
    private ComboBox<Customer> customer;
    @FXML
    private TextField item_name;
    @FXML
    private TextField prev_due;
    @FXML
    private TextField amount;
    @FXML
    private DatePicker rcv_date;
    @FXML
    private TextArea due_note;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = FXCollections.observableArrayList();
        sl.setCellValueFactory(new PropertyValueFactory("sl"));
        date.setCellValueFactory(new PropertyValueFactory("date"));
        item.setCellValueFactory(new PropertyValueFactory("item"));
        caret.setCellValueFactory(new PropertyValueFactory("caret"));
        quantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        rate.setCellValueFactory(new PropertyValueFactory("rate"));
        paid.setCellValueFactory(new PropertyValueFactory("paid"));
        due.setCellValueFactory(new PropertyValueFactory("due"));
        loss.setCellValueFactory(new PropertyValueFactory("loss"));
        note.setCellValueFactory(new PropertyValueFactory("note"));
        
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "get/customer").asJson().getBody().getArray();
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                int id = obj.getInt("id");
                String name = obj.getString("name");
                
                this.customer.getItems().add(new Customer(0, id, name, "","",1));
            }
        } catch (UnirestException ex) {
            Logger.getLogger(DuePaymentVoucherController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
        this.sl.setEditable(true);
    }    

    @FXML
    private void onShowButtonClick(ActionEvent event) {
        this.table.getItems().clear();
        this.list.clear();
        if(this.customer.getSelectionModel().isEmpty()){
            Msg.showError("Please Select A Customer");
            return;
        }
        int customer_id = this.customer.getSelectionModel().getSelectedItem().getId();
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "get/due-voucher/" + customer_id).asJson().getBody().getArray();
            int sl = 1;
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                int id = obj.getInt("id");
                int caret = obj.getInt("caret");
                int quantity = obj.getInt("quantity");
                String date = obj.getString("date");
                String item = obj.getString("category");
                String note = obj.getString("note");
                float rate = Float.parseFloat(obj.get("rate").toString());
                float paid = Float.parseFloat(obj.get("paid").toString());
                float due = Float.parseFloat(obj.get("due").toString());
                float loss = Float.parseFloat(obj.get("loss").toString());
                
                this.list.add(new DuePayment(sl, id, caret, quantity, date, item, note, rate * quantity, paid, due, loss));
                
                sl++;
            }
        } catch (UnirestException ex) {
            Logger.getLogger(DuePaymentVoucherController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.table.getItems().addAll(list);
        }
        
    }

    @FXML
    private void onReceiveDueButtonClick(ActionEvent event) {
        if(this.table.getSelectionModel().isEmpty()){
            return;
        }
        int sell_id = this.table.getSelectionModel().getSelectedItem().getId();
        String date = this.rcv_date.getValue().toString();
        float amount = Float.parseFloat(this.amount.getText());
        String note = this.note.getText();
        
        try {
            String res = Unirest.post(MetaData.baseUrl + "add/due-voucher")
                    .field("sell_id", sell_id)
                    .field("date", date)
                    .field("amount", amount)
                    .field("note", note)
                    .asString().getBody();
            if(res.equals("1")){
                Msg.showInformation("Due has been received successfully.");
                onShowButtonClick(null);    
            }else{
                Msg.showError("");
            }
        } catch (UnirestException ex) {
            Logger.getLogger(DuePaymentVoucherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onMouseClick(MouseEvent event) {
        if(this.table.getSelectionModel().isEmpty()){
            return;
        }
        DuePayment duePayment = this.table.getSelectionModel().getSelectedItem();
        this.item_name.setText(duePayment.getItem());
        this.prev_due.setText(String.valueOf(duePayment.getDue()));
    }
    
}
