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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.LowStockReport;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class LowStockReportController implements Initializable {
    @FXML
    private TableView<LowStockReport> table;
    @FXML
    private TableColumn<LowStockReport, Integer> sl;
    @FXML
    private TableColumn<LowStockReport, String> item;
    @FXML
    private TableColumn<LowStockReport, Integer> warning_qty;
    @FXML
    private TableColumn<LowStockReport, Integer> current_qty;
    private List<LowStockReport> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = FXCollections.observableArrayList();
        sl.setCellValueFactory(new PropertyValueFactory("sl"));
        item.setCellValueFactory(new PropertyValueFactory("item"));
        warning_qty.setCellValueFactory(new PropertyValueFactory("warning_qty"));
        current_qty.setCellValueFactory(new PropertyValueFactory("current_qty"));
        
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "report/low-stock").asJson().getBody().getArray();
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                int sl = obj.getInt("sl");
                String item = obj.getString("name");
                int warning_qty = obj.getInt("warning_quantity");
                int current_qty = obj.getInt("current_quantity");
                
                this.list.add(new LowStockReport(sl, item, warning_qty, current_qty));
            }
        } catch (UnirestException ex) {
            Logger.getLogger(LowStockReportController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }finally{
            this.table.getItems().addAll(list);
        }
    }    
    
}
