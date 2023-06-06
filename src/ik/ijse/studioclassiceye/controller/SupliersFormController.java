package ik.ijse.studioclassiceye.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import ik.ijse.studioclassiceye.db.DBConnection;
import ik.ijse.studioclassiceye.model.CustomerFormModel;
import ik.ijse.studioclassiceye.model.SupplierFormModel;
import ik.ijse.studioclassiceye.tableModel.CustomerModel;
import ik.ijse.studioclassiceye.tableModel.SupplierModel;
import ik.ijse.studioclassiceye.to.Customer;
import ik.ijse.studioclassiceye.to.Suppliers;
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
import java.util.ArrayList;

public class SupliersFormController {

    @FXML
    private TableColumn<?, ?> clmSupId;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmAddress;

    @FXML
    private TableColumn<?, ?> clmContact;

    @FXML
    private AnchorPane panesup;

    @FXML
    private JFXTextField txtSupId;

    @FXML
    private JFXTextField txtSupName;

    @FXML
    private JFXTextField txtSupAddress;

    @FXML
    private JFXTextField txtSupContact;

    @FXML
    private JFXButton btnSupAdd;

    @FXML
    private JFXButton btnSupUpdate;

    @FXML
    private JFXButton btnSupDelete;

    @FXML
    private TableView<SupplierModel> tblSuppliers;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactoryCus();
        setObList();
        tblSuppliers.setItems(obList);
        txtSupId.requestFocus();
    }
    public void setCellValueFactoryCus(){
        clmSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("supName"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("supAddress"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("supContact"));
    }

    private ObservableList<SupplierModel> obList= FXCollections.observableArrayList();
    public ResultSet getTableValues() throws SQLException, ClassNotFoundException {
        String sql="select * from suppliers";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);
        return resultSet;
    }

    public ObservableList setObList() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = getTableValues();
        ArrayList arrayList = new ArrayList();
        while(resultSet.next()) {
            arrayList.add(resultSet.getString(1));
            arrayList.add(resultSet.getString(2));
            arrayList.add(resultSet.getString(3));
            arrayList.add(resultSet.getString(4));
            obList.add(new SupplierModel(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)));
        }
        for (int i=0;i<4;i++) {
            System.out.println();
        }
        return obList;
    }

    @FXML
    void btnSupAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String supId=txtSupId.getText();
        String supName=txtSupName.getText();
        String supAddress=txtSupAddress.getText();
        String supContact=txtSupContact.getText();
        if (supId.equals("")|supName.equals("")|supAddress.equals("")|supContact.equals("")) {
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Some data fields are empty");
            error.setContentText("Added Unsuccessfully");
            error.show();
        }else {
            Suppliers suppliers = new Suppliers(supId, supName, supAddress, supContact);
            new SupplierFormModel().addsuppliers(suppliers);
        }
        obList.clear();
        setObList();
    }

    @FXML
    void btnSupDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String supId=txtSupId.getText();

        if (supId.equals("")){
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Some data fields are empty");
            error.setContentText("Added Unsuccessfully");
            error.show();
        }else {
            Suppliers suppliers = new Suppliers(supId);
            new SupplierFormModel().deleteSuppliers(suppliers);
        }
        obList.clear();
        setObList();
    }

    @FXML
    void btnSupUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String supId=txtSupId.getText();
        String supName=txtSupName.getText();
        String supAddress=txtSupAddress.getText();
        String supContact=txtSupContact.getText();

        if (supId.equals("")|supName.equals("")|supAddress.equals("")|supContact.equals("")){
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Some data fields are empty");
            error.setContentText("Added Unsuccessfully");
            error.show();
        }else {
            Suppliers suppliers = new Suppliers(supId, supName, supAddress, supContact);
            SupplierFormModel supplierFormModel = new SupplierFormModel();
            supplierFormModel.suppliersUpdate(suppliers);
        }
        obList.clear();
        setObList();
    }

    @FXML
    void txtSupAddressOnAction(ActionEvent event) {
        txtSupContact.requestFocus();
    }

    @FXML
    void txtSupContactOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String supId=txtSupId.getText();
        String supName=txtSupName.getText();
        String supAddress=txtSupAddress.getText();
        String supContact=txtSupContact.getText();
        if (supId.equals("")|supName.equals("")|supAddress.equals("")|supContact.equals("")) {
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Some data fields are empty");
            error.setContentText("Added Unsuccessfully");
            error.show();
        }else {
            Suppliers suppliers = new Suppliers(supId, supName, supAddress, supContact);
            int i = new SupplierFormModel().addsuppliers(suppliers);

            if (i>0){
                txtSupId.clear();
                txtSupName.clear();
                txtSupAddress.clear();
                txtSupContact.clear();
                txtSupId.requestFocus();
            }
            obList.clear();
            setObList();
        }
    }

    @FXML
    void txtSupIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String supId=txtSupId.getText();

        Suppliers suppliers=new Suppliers(supId);
        ResultSet resultSet = new SupplierFormModel().searchSuppliers(suppliers);
        if (resultSet.next()){
            txtSupName.setText(resultSet.getString(2));
            txtSupAddress.setText(resultSet.getString(3));
            txtSupContact.setText(resultSet.getString(4));
        }else{
            txtSupName.requestFocus();
        }
    }

    @FXML
    void txtSupNameOnAction(ActionEvent event) {
        txtSupAddress.requestFocus();
    }



}
