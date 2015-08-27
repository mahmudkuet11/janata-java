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
import model.StockReport;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class StockReportController implements Initializable {
    @FXML
    private TableView<StockReport> table;
    @FXML
    private TableColumn<StockReport, Integer> sl;
    @FXML
    private TableColumn<StockReport, String> item;
    @FXML
    private TableColumn<StockReport, Integer> caret18;
    @FXML
    private TableColumn<StockReport, Integer> caret21;
    @FXML
    private TableColumn<StockReport, Integer> caret22;
    @FXML
    private TableColumn<StockReport, Integer> total;
    private List<StockReport> list;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = FXCollections.observableArrayList();
        
        sl.setCellValueFactory(new PropertyValueFactory("sl"));
        item.setCellValueFactory(new PropertyValueFactory("item"));
        caret18.setCellValueFactory(new PropertyValueFactory("caret18"));
        caret21.setCellValueFactory(new PropertyValueFactory("caret21"));
        caret22.setCellValueFactory(new PropertyValueFactory("caret22"));
        total.setCellValueFactory(new PropertyValueFactory("total"));
        
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "report/stock").asJson().getBody().getArray();
            for(int i=0; i<res.length(); i++){
                JSONObject obj = res.getJSONObject(i);
                int sl = obj.getInt("sl");
                String item = obj.getString("category");
                int caret18 = obj.getInt("caret18");
                int caret21 = obj.getInt("caret21");
                int caret22 = obj.getInt("caret22");
                int total = obj.getInt("total");
                
                this.list.add(new StockReport(sl, item, caret18, caret21, caret22, total));
            }
        } catch (UnirestException ex) {
            Logger.getLogger(StockReportController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }finally{
            this.table.getItems().addAll(list);
        }
    }    
    
}
