package ik.ijse.studioclassiceye.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import ik.ijse.studioclassiceye.db.DBConnection;
import ik.ijse.studioclassiceye.model.PackageFormModel;
import ik.ijse.studioclassiceye.tableModel.PackageModel;
import ik.ijse.studioclassiceye.to.Package;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PackageFormController {

    @FXML
    private AnchorPane panecus;

    @FXML
    private JFXTextField txtPackageId;

    @FXML
    private JFXTextField txtPackageDescription;

    @FXML
    private JFXTextField txtPackagePrice;

    @FXML
    private JFXButton btnAddPackage;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TableView<PackageModel> tblPackage;

    @FXML
    private TableColumn<PackageModel,String> clmPackageId;

    @FXML
    private TableColumn<PackageModel,String> clmPackageDescription;

    @FXML
    private TableColumn<PackageModel,String> clmPackagePrice;

    @FXML
    void txtPackageDescriptionOnAction(ActionEvent event) {
        txtPackageDescription.requestFocus();
    }

    @FXML
    void txtPackagePriceOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String packageId=txtPackageId.getText();
        String packageDescription=txtPackageDescription.getText();
        String packagePrice=txtPackagePrice.getText();


        if (packageId.equals("")|packageDescription.equals("")|packagePrice.equals("")){
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Some data fields are empty");
            error.setContentText("Added Unsuccessfully");
            error.show();
        }else {
            Package Package = new Package(packageId,packageDescription,packagePrice);
            int i=new PackageFormModel().addPackage(Package);

            if (i>0){
                txtPackageId.clear();
                txtPackageDescription.clear();
                txtPackagePrice.clear();
                txtPackageId.requestFocus();
            }
            obList.clear();
            setObList();
        }
    }


    public void initialize() throws SQLException, ClassNotFoundException {
        txtPackageId.requestFocus();
        setCellValueFactoryCus();
        setObList();
        tblPackage.setItems(obList);
    }

    public void setCellValueFactoryCus(){
        clmPackageId.setCellValueFactory(new PropertyValueFactory<>("packageId"));
        clmPackageDescription.setCellValueFactory(new PropertyValueFactory<>("packageDescription"));
        clmPackagePrice.setCellValueFactory(new PropertyValueFactory<>("packagePrice"));

    }

    private ObservableList<PackageModel> obList= FXCollections.observableArrayList();
    public ResultSet getTableValues() throws SQLException, ClassNotFoundException {
        String sql="select * from packages";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);
        return resultSet;
    }

    public ObservableList setObList() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = getTableValues();
        while(resultSet.next()) {
            obList.add(new PackageModel(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
        }
        txtPackageId.requestFocus();
        return obList;
    }

    @FXML
    void btnAddPackageOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String packageId=txtPackageId.getText();
        String packageDescription=txtPackageDescription.getText();
        String packagePrice=txtPackagePrice.getText();


        if (packageId.equals("")|packageDescription.equals("")|packagePrice.equals("")){
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Some data fields are empty");
            error.setContentText("Added Unsuccessfully");
            error.show();
        }else {
            Package Package = new Package(packageId,packageDescription,packagePrice);
            int i=new PackageFormModel().addPackage(Package);

            if (i>0){
                txtPackageId.clear();
                txtPackageDescription.clear();
                txtPackagePrice.clear();
                txtPackageId.requestFocus();
            }
            obList.clear();
            setObList();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String packageId=txtPackageId.getText();

        Package packages=new Package(packageId);
        new PackageFormModel().packageDelete(packages);
        obList.clear();
        setObList();
        txtPackageId.clear();
        txtPackageId.requestFocus();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String packageId=txtPackageId.getText();
        String packageDescription=txtPackageDescription.getText();
        String packagePrice=txtPackagePrice.getText();


        if (packageId.equals("")|packageDescription.equals("")|packagePrice.equals("")){
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Some data fields are empty");
            error.setContentText("Update Unsuccessfully");
            error.show();
        }else {
            Package Package = new Package(packageId,packageDescription,packagePrice);
            int i=new PackageFormModel().updatePackage(Package);

            if (i>0){
                txtPackageId.clear();
                txtPackageDescription.clear();
                txtPackagePrice.clear();
                txtPackageId.requestFocus();
            }
            obList.clear();
            setObList();
        }
    }

    @FXML
    void txtPackageIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String packageId=txtPackageId.getText();

        Package packages=new Package(packageId);
        ResultSet resultSet = new PackageFormModel().searchPackages(packages);
        if (resultSet.next()){
            txtPackageDescription.setText(resultSet.getString(2));
            txtPackagePrice.setText(resultSet.getString(3));

        }else{
            txtPackageDescription.requestFocus();
        }
    }


}
