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

    @FXML
    private void onDeleteCategoryClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/DeleteCategory.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Delete Category");
    }

    @FXML
    private void onAddNewExpenseCategoryClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddNewExpenseCategory.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Add New Expense Category");
    }

    @FXML
    private void onViewExpenseCategoryClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ViewExpenseCategory.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("View Expense Category");
    }

    @FXML
    private void onEditExpenseCategoryClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/EditExpenseCategory.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Edit Expense Category");
    }

    @FXML
    private void onDeleteExpenseCategoryClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/DeleteExpenseCategory.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Delete Expense Category");
    }

    @FXML
    private void onAddNewPurchaseVoucherClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/NewPurchaseVoucher.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("New Purchase Voucher");
    }

    @FXML
    private void onViewPurchaseVoucherClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ViewPurchaseVoucher.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("View Purchase Voucher");
    }

    @FXML
    private void onAddNewSaleVoucherClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/NewSellVoucher.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("New Sale Voucher");
    }

    @FXML
    private void onDuePaymentClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/DuePaymentVoucher.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Due Payment");
    }

    @FXML
    private void onExpenseVoucherClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/NewExpenseVoucher.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Expense Voucher");
    }

    @FXML
    private void onPurchaseReportClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/PurchaseReport.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Purchase Report");
    }

    @FXML
    private void onSupplierWisePurchaseReportClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/SupplierWisePurchaseReport.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Supplier Wise Purchase Report");
    }

    @FXML
    private void onSellReportClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/SalesReport.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Sales Report");
    }

    @FXML
    private void onCustomerWiseSellReportClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerWiseSalesReport.fxml"));
        Scene scene = menubar.getScene();
        Stage stage = (Stage)menubar.getScene().getWindow();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Customer Wise Sales Report");
    }
    
}
