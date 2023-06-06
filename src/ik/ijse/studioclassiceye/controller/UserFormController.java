package ik.ijse.studioclassiceye.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import ik.ijse.studioclassiceye.db.DBConnection;
import ik.ijse.studioclassiceye.model.UserFormModel;
import ik.ijse.studioclassiceye.tableModel.CustomerModel;
import ik.ijse.studioclassiceye.tableModel.UserModel;
import ik.ijse.studioclassiceye.to.User;
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

public class UserFormController {
    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactoryCus();
        setEmpId();
        setRole();
        tblUser.setItems(setObList());
    }
    public void setCellValueFactoryCus(){
        clmUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        clmEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        clmDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        clmUserRole.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

    private ObservableList<UserModel> obList= FXCollections.observableArrayList();
    public ResultSet getTableValues() throws SQLException, ClassNotFoundException {
        String sql="select * from user";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);
        return resultSet;
    }

    public ObservableList setObList() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = getTableValues();
        while(resultSet.next()) {

            obList.add(new UserModel(resultSet.getString(1),resultSet.getString(2),resultSet.getString(4),resultSet.getString(5)));
        }
        cmbEmpId.requestFocus();
        return obList;
    }

    @FXML
    private AnchorPane panecus;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXButton btnAddUser;

    @FXML
    private JFXButton btnUpdateUser;

    @FXML
    private JFXButton btnDeleteUser;

    @FXML
    private JFXComboBox<?> cmbEmpId;

    @FXML
    private JFXDatePicker dateDateOfBirth;

    @FXML
    private JFXComboBox<?> cmbUserRole;

    @FXML
    private TableView<?> tblUser;

    @FXML
    private TableColumn<UserModel, String> clmUserName;

    @FXML
    private TableColumn<UserModel, String> clmEmpId;

    @FXML
    private TableColumn<UserModel, String> clmDateOfBirth;

    @FXML
    private TableColumn<UserModel, String> clmUserRole;



    @FXML
    void btnAddUserOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String empId= (String) cmbEmpId.getValue();
        String userName=txtUserName.getText();
        String password=txtPassword.getText();
        String role= (String) cmbUserRole.getValue();
        String dateOfBirth= String.valueOf(dateDateOfBirth.getValue());

        if (empId.equals("")|userName.equals("")|password.equals("")|role.equals("")|dateOfBirth.equals("")){
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Some data fields are empty");
            error.setContentText("Added Unsuccessfully");
            error.show();
        }else {

        User user=new User(empId,userName,password,role,dateOfBirth);
        int i = new UserFormModel().addUser(user);
        if (i>0) {
            txtUserName.clear();
            txtPassword.clear();
            cmbUserRole.setPromptText("Select role");
            cmbEmpId.setPromptText("Set Emp_Id");
            obList.clear();
            setObList();
        }
        }
    }

    @FXML
    void btnDeleteUserOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String userName=txtUserName.getText();

        User user=new User(userName);
        new UserFormModel().deleteUser(user);
        obList.clear();
        setObList();
    }

    @FXML
    void btnUpdateUserOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String empId= (String) cmbEmpId.getValue();
        String userName=txtUserName.getText();
        String password=txtPassword.getText();
        String role= (String) cmbUserRole.getValue();
        String dateOfBirth= String.valueOf(dateDateOfBirth.getValue());

        if (empId.equals("")|userName.equals("")|password.equals("")|role.equals("")|dateOfBirth.equals("")){
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Some data fields are empty");
            error.setContentText("Update Unsuccessfully");
            error.show();
        }else {
            User user = new User(empId, userName, password, role, dateOfBirth);
            int i = new UserFormModel().updateUser(user);
        }
        obList.clear();
        setObList();
    }

    @FXML
    void dateDateOfBirthOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }
    private ObservableList obListEmpId = FXCollections.observableArrayList();
    public void setEmpId() throws SQLException, ClassNotFoundException {
        String sql="select emp_Id from employee";
        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);
        ArrayList <String>arrayList=new ArrayList();
        while(resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        for (String id: arrayList){
            obListEmpId.add(id);
        }
        cmbEmpId.setItems(obListEmpId);
    }

    private ObservableList role= FXCollections.observableArrayList();
    public void setRole(){

        cmbUserRole.setPromptText("Select role");
        role.add("Admin");
        role.add("Reception");


        cmbUserRole.setItems(role);
    }

}
