package ik.ijse.studioclassiceye.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import ik.ijse.studioclassiceye.db.DBConnection;
import ik.ijse.studioclassiceye.model.EmployeeFormModel;
import ik.ijse.studioclassiceye.tableModel.EmployeeModel;
import ik.ijse.studioclassiceye.to.Employee;
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
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeFormController {
    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactoryCus();
        setRole();
        setObList();
        tblEmp.setItems(obList);
        txtEmployeeId.requestFocus();
    }
    public void setCellValueFactoryCus(){
        clmEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        clmEmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        clmEmpAddress.setCellValueFactory(new PropertyValueFactory<>("empAddress"));
        clmEmpContact.setCellValueFactory(new PropertyValueFactory<>("empContact"));
        clmEmpSalary.setCellValueFactory(new PropertyValueFactory<>("empSalary"));
        clmEmpRole.setCellValueFactory(new PropertyValueFactory<>("empRole"));
    }

    private ObservableList<EmployeeModel> obList= FXCollections.observableArrayList();
    public ResultSet getTableValues() throws SQLException, ClassNotFoundException {
        String sql="select * from employee";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);
        return resultSet;
    }

    public ObservableList setObList() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = getTableValues();

        while(resultSet.next()) {
            obList.add(new EmployeeModel(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)));
        }

        return obList;
    }


    @FXML
    private JFXComboBox<?> cmbEmpRole;

    @FXML
    private AnchorPane panecus;

    @FXML
    private JFXTextField txtEmployeeId;

    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private JFXTextField txtEmployeeAddress;

    @FXML
    private JFXTextField txtEmployeeContact;

    @FXML
    private JFXButton btnEmployeeAdd;

    @FXML
    private JFXButton btnEmployeeUpdate;

    @FXML
    private JFXButton btnEmployeeDelete;

    @FXML
    private JFXTextField txtEmployeeSalary;

    @FXML
    private TableView<EmployeeModel> tblEmp;

    @FXML
    private TableColumn<?, ?> clmEmpId;

    @FXML
    private TableColumn<?, ?> clmEmpName;

    @FXML
    private TableColumn<?, ?> clmEmpAddress;

    @FXML
    private TableColumn<?, ?> clmEmpContact;

    @FXML
    private TableColumn<?, ?> clmEmpSalary;

    @FXML
    private TableColumn<?, ?> clmEmpRole;

    @FXML
    private Label lblEmpId;

    @FXML
    private Label lblEmpName;

    @FXML
    private Label lblEmpContact;

    @FXML
    private Label lblEmpSalary;

    @FXML
    private Label lblEmpRole;

    @FXML
    void btnEmployeeAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String empId=txtEmployeeId.getText();
        String empName=txtEmployeeName.getText();
        String empAddress=txtEmployeeAddress.getText();
        String empContact=txtEmployeeContact.getText();
        String empSalary= txtEmployeeSalary.getText();
        String role= (String) cmbEmpRole.getValue();

        if (empId.equals("")|empName.equals("")|empAddress.equals("")|empContact.equals("")|empSalary.equals("")|role.equals("")){
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Some data fields are empty");
            error.setContentText("Added Unsuccessfully");
            error.show();
        }else {
                    if (ValidityCheck.checkEmpId(empId)) {
                        lblEmpId.setVisible(false);
                        if (ValidityCheck.checkName(empName)) {
                            lblEmpName.setVisible(false);
                            if (ValidityCheck.checkContact(empContact)) {
                                lblEmpContact.setVisible(false);
                                if (ValidityCheck.checkSalary(empSalary)) {
                                    lblEmpSalary.setVisible(false);
                                    Employee employee = new Employee(empId, empName, empAddress, empContact, empSalary, role);
                                    EmployeeFormModel employeeFormModel = new EmployeeFormModel();
                                    int i = employeeFormModel.employeeAdd(employee);
                                    if (i > 0) {
                                        txtEmployeeId.clear();
                                        txtEmployeeName.clear();
                                        txtEmployeeAddress.clear();
                                        txtEmployeeContact.clear();
                                        txtEmployeeSalary.clear();
                                        cmbEmpRole.setPromptText("Select role");
                                        txtEmployeeId.requestFocus();
                                    }
                                }else {
                                    lblEmpSalary.setVisible(true);
                                    txtEmployeeSalary.requestFocus();
                                }
                            }else {
                                lblEmpContact.setVisible(true);
                                txtEmployeeContact.requestFocus();
                            }
                        }else{
                            lblEmpName.setVisible(true);
                            txtEmployeeName.requestFocus();
                        }
                    }else{
                        lblEmpId.setVisible(true);
                        txtEmployeeId.requestFocus();
                    }


        }
        obList.clear();
        setObList();
    }

    @FXML
    void btnEmployeeDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String empId = txtEmployeeId.getText();
        if (ValidityCheck.checkEmpId(empId)) {
            lblEmpId.setVisible(false);
            boolean isDeleted = new EmployeeFormModel().deleteEmployee(empId);
            if (isDeleted) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Employee Delete Massage");
                alert.setHeaderText("Delete the Employee");
                alert.setContentText("Employee Deleted Successfully!");
                alert.show();
            } else {
                Alert error = new Alert(Alert.AlertType.WARNING);
                error.setTitle("Error");
                error.setHeaderText("Something Went Wrong");
                error.setContentText("Deleted Unsuccessfully");
                error.show();
            }
        }else {
            lblEmpId.setVisible(true);
        }
    }

    @FXML
    void btnEmployeeUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String empId=txtEmployeeId.getText();
        String empName=txtEmployeeName.getText();
        String empAddress=txtEmployeeAddress.getText();
        String empContact=txtEmployeeContact.getText();
        String empSalary= txtEmployeeSalary.getText();
        String role= (String) cmbEmpRole.getValue();


        if (empId.equals("")|empName.equals("")|empAddress.equals("")|empContact.equals("")|empSalary.equals("")|role.equals("")){
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Some data fields are empty");
            error.setContentText("Update Unsuccessfully");
            error.show();
        }else {
            Employee employee = new Employee(empId, empName, empAddress, empContact,empSalary,role);
            new EmployeeFormModel().employeeUpdate(employee);
        }
        obList.clear();
        setObList();
    }

    @FXML
    void tblEmpOnAction(ActionEvent event) {

    }

    @FXML
    void txtEmployeeAddressOnAction(ActionEvent event) {
        txtEmployeeContact.requestFocus();
    }

    @FXML
    void txtEmployeeContactOnAction(ActionEvent event) {
        if (ValidityCheck.checkSalary(txtEmployeeSalary.getText())) {
            lblEmpSalary.setVisible(false);
            txtEmployeeSalary.requestFocus();
        }else{
            lblEmpSalary.setVisible(true);
        }
    }

    @FXML
    void txtEmployeeIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String empId=txtEmployeeId.getText();
        if (ValidityCheck.checkEmpId(empId)){
            lblEmpId.setVisible(false);
            Employee employee = new Employee(empId);
            ResultSet resultSet = new EmployeeFormModel().searchEmployee(employee);
            if (resultSet.next()) {
                txtEmployeeName.setText(resultSet.getString(2));
                txtEmployeeAddress.setText(resultSet.getString(3));
                txtEmployeeContact.setText(resultSet.getString(4));
                txtEmployeeSalary.setText(resultSet.getString(5));
                String s = resultSet.getString(6);
                cmbEmpRole.setPromptText(s);
            } else {
                txtEmployeeName.requestFocus();
            }
        }else {
            lblEmpId.setVisible(true);

        }
    }

    @FXML
    void txtEmployeeNameOnAction(ActionEvent event){
        if (ValidityCheck.checkName(txtEmployeeName.getText())) {
            lblEmpName.setVisible(false);
            txtEmployeeAddress.requestFocus();
        }else{
            lblEmpName.setVisible(true);
        }
    }

    @FXML
    void txtEmployeeSalaryOnAction(ActionEvent event) {
        cmbEmpRole.requestFocus();
    }

    @FXML
    void cmbEmpRoleOnAction(ActionEvent event) {

    }
    private ObservableList role= FXCollections.observableArrayList();
    public void setRole(){

        cmbEmpRole.setPromptText("Select role");
        role.add("Admin");
        role.add("Reception");
        role.add("Photographer");
        role.add("Helper");
        role.add("Graphic_designer");

        cmbEmpRole.setItems(role);
    }



}
