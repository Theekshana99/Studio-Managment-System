package ik.ijse.studioclassiceye.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import ik.ijse.studioclassiceye.db.DBConnection;
import ik.ijse.studioclassiceye.model.CustomerFormModel;
import ik.ijse.studioclassiceye.to.Customer;
import ik.ijse.studioclassiceye.util.ValidityCheck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import ik.ijse.studioclassiceye.tableModel.CustomerModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerFormController {
    @FXML
    private Label lclCusId;

    @FXML
    private Label lclCusName;

    @FXML
    private Label lclCusContact;

    @FXML
    private TableColumn<CustomerModel, String> customerId;

    @FXML
    private TableColumn<CustomerModel, String> customerName;

    @FXML
    private TableColumn<CustomerModel, String> customerAddress;

    @FXML
    private TableColumn<CustomerModel, String> customerContact;

    @FXML
    private AnchorPane panecus;

    @FXML
    private JFXTextField txtCusId;

    @FXML
    private JFXTextField txtCusName;

    @FXML
    private JFXTextField txtCusAddress;

    @FXML
    private JFXTextField txtCusContact;

    @FXML
    private JFXButton btnCusAdd;

    @FXML
    private JFXButton btnCusUpdate;

    @FXML
    private JFXButton btnCusDelete;

    @FXML
    private TableView<CustomerModel> tblCustomer;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactoryCus();
        setObList();
        tblCustomer.setItems(obList);
    }

    public void setCellValueFactoryCus(){
        customerId.setCellValueFactory(new PropertyValueFactory<>("cus_Id"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    private ObservableList<CustomerModel> obList= FXCollections.observableArrayList();
    public ResultSet getTableValues() throws SQLException, ClassNotFoundException {
        String sql="select * from customer";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);
        return resultSet;
    }

    public ObservableList setObList() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = getTableValues();
        while(resultSet.next()) {

            obList.add(new CustomerModel(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)));
        }
        txtCusId.requestFocus();
        return obList;
    }

    @FXML
    void btnCusAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
         String cusId=txtCusId.getText();
        String cusName=txtCusName.getText();
        String cusAddress=txtCusAddress.getText();
        String cusContact=txtCusContact.getText();

        if (cusId.equals("")|cusName.equals("")|cusAddress.equals("")|cusContact.equals("")){
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Some data fields are empty");
            error.setContentText("Added Unsuccessfully");
            error.show();
        }else {
            if (ValidityCheck.checkCusId(cusId)) {
                lclCusId.setVisible(false);
                if (ValidityCheck.checkName(cusName)) {
                    lclCusName.setVisible(false);
                    if (ValidityCheck.checkContact(cusContact)) {
                        lclCusContact.setVisible(false);
                        Customer customer = new Customer(cusId, cusName, cusAddress, cusContact);
                        CustomerFormModel customerFormModel = new CustomerFormModel();
                        int i = customerFormModel.customerAdd(customer);
                        if (i > 0) {
                            txtCusId.clear();
                            txtCusName.clear();
                            txtCusAddress.clear();
                            txtCusContact.clear();
                            txtCusId.requestFocus();
                        }
                        obList.clear();
                        setObList();
                    } else {
                        lclCusContact.setVisible(true);
                    }
                } else {
                    lclCusName.setVisible(true);
                }
            }else {
                lclCusId.setVisible(true);
            }
        }
    }

    @FXML
    void btnCusDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String cusId=txtCusId.getText();

        Customer customer=new Customer(cusId);
        new CustomerFormModel().customerDelete(customer);
        obList.clear();
        setObList();
    }

    @FXML
    void btnCusUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String cusId=txtCusId.getText();
        String cusName=txtCusName.getText();
        String cusAddress=txtCusAddress.getText();
        String cusContact=txtCusContact.getText();

        if (cusId.equals("")|cusName.equals("")|cusAddress.equals("")|cusContact.equals("")){
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Some data fields are empty");
            error.setContentText("Added Unsuccessfully");
            error.show();
        }else {
            Customer customer = new Customer(cusId, cusName, cusAddress, cusContact);
            CustomerFormModel customerFormModel = new CustomerFormModel();
            customerFormModel.customerUpdate(customer);
        }
        obList.clear();
        setObList();
    }

    @FXML
    void txtCusAddressOnAction(ActionEvent event) {
        txtCusContact.requestFocus();
    }

    @FXML
    void txtCusContactOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String cusId=txtCusId.getText();
        String cusName=txtCusName.getText();
        String cusAddress=txtCusAddress.getText();
        String cusContact=txtCusContact.getText();

        if (ValidityCheck.checkContact(cusContact)) {
            lclCusContact.setVisible(false);
            if (cusId.equals("") | cusName.equals("") | cusAddress.equals("") | cusContact.equals("")) {
                Alert error = new Alert(Alert.AlertType.WARNING);
                error.setTitle("Error");
                error.setHeaderText("Some data fields are empty");
                error.setContentText("Added Unsuccessfully");
                error.show();
            } else {
                if (ValidityCheck.checkCusId(cusId)) {
                    lclCusId.setVisible(false);
                    if (ValidityCheck.checkName(cusName)) {
                        lclCusName.setVisible(false);
                        if (ValidityCheck.checkContact(cusContact)) {
                            lclCusContact.setVisible(false);
                            Customer customer = new Customer(cusId, cusName, cusAddress, cusContact);
                            CustomerFormModel customerFormModel = new CustomerFormModel();
                            int i = customerFormModel.customerAdd(customer);
                            if (i > 0) {
                                txtCusId.clear();
                                txtCusName.clear();
                                txtCusAddress.clear();
                                txtCusContact.clear();
                                txtCusId.requestFocus();
                            }
                            obList.clear();
                            setObList();
                        } else {
                            lclCusContact.setVisible(true);
                            txtCusContact.requestFocus();
                        }
                    } else {
                        lclCusName.setVisible(true);
                        txtCusName.requestFocus();
                    }
                } else {
                    lclCusId.setVisible(true);
                    txtCusId.requestFocus();
                }
            }
        }else {
            lclCusContact.setVisible(true);
        }
    }

    @FXML
    void txtCusIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String cusId=txtCusId.getText();
        if (ValidityCheck.checkCusId(cusId)) {
            lclCusId.setVisible(false);
            Customer customer = new Customer(cusId);
            ResultSet resultSet = new CustomerFormModel().searchCustomer(customer);
            if (resultSet.next()) {
                txtCusName.setText(resultSet.getString(2));
                txtCusAddress.setText(resultSet.getString(3));
                txtCusContact.setText(resultSet.getString(4));
            } else {
                txtCusName.requestFocus();
            }
        }else{
            lclCusId.setVisible(true);
        }
    }

    @FXML
    void txtCusNameOnAction(ActionEvent event) {
        if (ValidityCheck.checkName(txtCusName.getText())) {
            lclCusName.setVisible(false);
            txtCusAddress.requestFocus();
        }else {
            lclCusName.setVisible(true);
        }
    }

    @FXML
    void lblCusContactOnAction(KeyEvent event) {

    }

    @FXML
    void lblCusIdOnAction(KeyEvent event) {

    }

    @FXML
    void lblCusNameOnAction(KeyEvent event) {

    }

    
}

