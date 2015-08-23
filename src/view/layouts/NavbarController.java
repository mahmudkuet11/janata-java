/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.layouts;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohar
 */
public class NavbarController implements Initializable {
    @FXML
    private MenuBar menubar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onAddNewCustomerClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddNewCustomer.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Add New Customer");
    }

    @FXML
    private void onViewCustomerClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ViewCustomer.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("View All Customers");
    }

    @FXML
    private void onEditCustomerClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/EditCustomer.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Edit Customer");
    }

    @FXML
    private void onDeleteCustomerClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/DeleteCustomer.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Delete Customer");
    }

    @FXML
    private void onAddNewSupplierClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddNewSupplier.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Add New Supplier");
    }

    @FXML
    private void onViewSupplierClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ViewSupplier.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Add New Supplier");
    }

    @FXML
    private void onEditSupplierClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/EditSupplier.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Edit Supplier");
    }

    @FXML
    private void onDeleteSupplierClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/DeleteSupplier.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Delete Supplier");
    }

    @FXML
    private void onAddNewCategoryClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddNewCategory.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Add New Item's Category");
    }

    @FXML
    private void onViewCategoryClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ViewCategory.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("View Category");
    }

    @FXML
    private void onEditCategoryClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/EditCategory.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Edit Category");
    }
    
}
