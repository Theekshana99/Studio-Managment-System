package ik.ijse.studioclassiceye.controller;

import com.jfoenix.controls.JFXButton;
import ik.ijse.studioclassiceye.util.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashBordAdminFormControlle {



    public JFXButton btnProduct;
    public AnchorPane pane2;
    @FXML
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton btnCusManage;

    @FXML
    private JFXButton btnAppointment;

    @FXML
    private JFXButton btnPackageManage;

    @FXML
    private JFXButton btnPayment;

    @FXML
    private Label lbl;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    void btnAppointmentOnAction(ActionEvent event) throws IOException {
        AnchorPane rootpanea= FXMLLoader.load(getClass().getResource("/ik/ijse/studioclassiceye/view/appointmentForm.fxml"));
        pane2.getChildren().setAll(rootpanea);
    }

    @FXML
    void btnCusManageOnAction(ActionEvent event) throws IOException {
        AnchorPane rootpane= FXMLLoader.load(getClass().getResource("/ik/ijse/studioclassiceye/view/customerForm.fxml"));
        pane2.getChildren().setAll(rootpane);
    }

    @FXML
    void btnPackageManageOnAction(ActionEvent event) throws IOException {
        AnchorPane rootpanepa= FXMLLoader.load(getClass().getResource("/ik/ijse/studioclassiceye/view/packageForm.fxml"));
        pane2.getChildren().setAll(rootpanepa);
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        AnchorPane rootpanepay= FXMLLoader.load(getClass().getResource("/ik/ijse/studioclassiceye/view/paymentsForm.fxml"));
        pane2.getChildren().setAll(rootpanepay);
    }

    @FXML
    void btnProductOnAction(ActionEvent event) throws IOException {
        AnchorPane rootpanep= FXMLLoader.load(getClass().getResource("/ik/ijse/studioclassiceye/view/productForm.fxml"));
        pane2.getChildren().setAll(rootpanep);
    }

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        AnchorPane rootpaneI= FXMLLoader.load(getClass().getResource("/ik/ijse/studioclassiceye/view/itemForm.fxml"));
        pane2.getChildren().setAll(rootpaneI);
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        AnchorPane rootpaneE= FXMLLoader.load(getClass().getResource("/ik/ijse/studioclassiceye/view/employeeForm.fxml"));
        pane2.getChildren().setAll(rootpaneE);
    }

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        AnchorPane rootpaneU= FXMLLoader.load(getClass().getResource("/ik/ijse/studioclassiceye/view/userForm.fxml"));
        pane2.getChildren().setAll(rootpaneU);
    }

    @FXML
    void btnSuplierOnAction(ActionEvent event) throws IOException {
        AnchorPane rootpaneS= FXMLLoader.load(getClass().getResource("/ik/ijse/studioclassiceye/view/supliersForm.fxml"));
        pane2.getChildren().setAll(rootpaneS);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootpanelogin=FXMLLoader.load(getClass().getResource("/ik/ijse/studioclassiceye/view/loginForm.fxml"));
        pane1.getChildren().setAll(rootpanelogin);
    }


}
