package ik.ijse.studioclassiceye.controller;

import ik.ijse.studioclassiceye.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginFormController {

    @FXML
    private Label lblPasswordAlert;

    @FXML
    private Label lblUserNameAlert;

    @FXML
    private CheckBox checkBox1;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLoging;

    @FXML
    void btnLogingOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        boolean isMatchPassword=matchPassword();
        if (isMatchPassword) {
            String userRole = getUserRole();
            if (userRole.equals("Admin")) {
                AnchorPane dashpane = FXMLLoader.load(getClass().getResource("/ik/ijse/studioclassiceye/view/dashBordAdminForm.fxml"));
                pane.getChildren().setAll(dashpane);
            } else if (userRole.equals("Reception")) {
                AnchorPane dashpane = FXMLLoader.load(getClass().getResource("/ik/ijse/studioclassiceye/view/dashBordReceptionForm.fxml"));
                pane.getChildren().setAll(dashpane);
            }
        }else{
            lblPasswordAlert.setText("Incorrect Password!");
        }
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        String errorStyle = String.format("-fx-border-color:red;-fx-background-radius: 40;-fx-border-radius: 40; -fx-border-width: 2;");
        String successStyle = String.format("-fx-border-color:  #4CC552;-fx-background-radius: 40; -fx-border-radius: 40; -fx-border-width: 2;");

        boolean isMatchPassword=matchPassword();
        if (isMatchPassword) {
            String userRole = getUserRole();
            if (userRole.equals("Admin")) {
                AnchorPane dashpane = FXMLLoader.load(getClass().getResource("/ik/ijse/studioclassiceye/view/dashBordAdminForm.fxml"));
                pane.getChildren().setAll(dashpane);
            } else if (userRole.equals("Reception")) {
                AnchorPane dashpane = FXMLLoader.load(getClass().getResource("/ik/ijse/studioclassiceye/view/dashBordReceptionForm.fxml"));
                pane.getChildren().setAll(dashpane);
            }
        }
    }

    private boolean matchPassword() throws SQLException, ClassNotFoundException {
        String errorStyle = String.format("-fx-border-color:red;-fx-background-radius: 40;-fx-border-radius: 40; -fx-border-width: 2;");
        String successStyle = String.format("-fx-border-color:  #4CC552;-fx-background-radius: 40; -fx-border-radius: 40; -fx-border-width: 2;");

        String password = txtPassword.getText();
        String name = txtUserName.getText();
        if (password.equals("")){
            lblPasswordAlert.setText("Password id required!");
        }else {
            String sql = "select password from user where user_Name=?";
            PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
            pstm.setObject(1, name);
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                if (password.equals(resultSet.getString(1))) {
                    return true;
                }else {
                    txtPassword.setStyle(errorStyle);
                    lblPasswordAlert.setText("Incorrect Password!");
                }
            }
        }
        return false;
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String errorStyle = String.format("-fx-border-color:red;-fx-background-radius: 40;-fx-border-radius: 40; -fx-border-width: 2;");
        String successStyle = String.format("-fx-border-color:  #4CC552;-fx-background-radius: 40; -fx-border-radius: 40; -fx-border-width: 2;");
        String userName=txtUserName.getText();
        if (userName.equals("")){
            lblUserNameAlert.setText("User name is required!");
        }else{
            boolean isUserNameExists=searchUserName(userName);
            if (isUserNameExists){
                lblUserNameAlert.setText("");
                txtPassword.requestFocus();
                txtUserName.setStyle(successStyle);
            }else{
                txtUserName.setStyle(errorStyle);
                lblUserNameAlert.setText("Invalid user name!");
                }
            }
        }



    private boolean searchUserName(String userName) throws SQLException, ClassNotFoundException {
        String sql="select user_Name from user";
        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);
        while(resultSet.next()){
            if (userName.equals(resultSet.getString(1))){
                return true;
            }
        }
        return false;
    }

    @FXML
    void checkBox1OnAction(ActionEvent event) {

    }

    private String getUserRole() throws SQLException, ClassNotFoundException {
        String userName=txtUserName.getText();
        String password=txtPassword.getText();

            String sql = "select password,role from user where user_Name=?;";
            PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
            pstm.setObject(1, userName);
            ResultSet resultSet = pstm.executeQuery();

            if (resultSet.next()) {
                boolean checkPassword = checkPassword(password, resultSet);
                if (checkPassword) {
                    return resultSet.getString(2);
                }else {
                    Alert error=new Alert(Alert.AlertType.WARNING);
                    error.setTitle("Error");
                    error.setHeaderText("Incorrect Password");
                    error.setContentText("User name and password not matched");
                    error.show();
                }

            } else {
                Alert error = new Alert(Alert.AlertType.WARNING);
                error.setTitle("Error");
                error.setHeaderText("Incorrect User name!");
                error.setContentText("Try Again!");
                error.show();
            }

        return null;
    }

    public boolean checkPassword(String password,ResultSet resultSet) throws SQLException {
        if (password.equals(resultSet.getString(1))){
            return true;
        }
    return false;
    }
}
