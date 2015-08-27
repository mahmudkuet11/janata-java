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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.JSONArray;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class DashboardController implements Initializable {
    @FXML
    private Label warning;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.warning.setVisible(false);
        try {
            JSONArray res = Unirest.get(MetaData.baseUrl + "report/low-stock").asJson().getBody().getArray();
            if(res.length() > 0){
                this.warning.setVisible(true);
            }
        } catch (UnirestException ex) {
            Logger.getLogger(LowStockReportController.class.getName()).log(Level.SEVERE, null, ex);
            Msg.showError("");
        }
    }    

    @FXML
    private void onWarningClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LowStockReport.fxml"));
        Scene scene = warning.getScene();
        Stage stage = (Stage)warning.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Low Stock Report");
    }
    
}
