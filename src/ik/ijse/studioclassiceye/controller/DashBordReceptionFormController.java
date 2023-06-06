package ik.ijse.studioclassiceye.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashBordReceptionFormController {



    public JFXButton btnProduct;
    public AnchorPane pane2;
    @FXML
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton btnCusManage;

    @FXML
    private JFXButton btnAppointmentManage;

    @FXML
    private JFXButton btnPackageManage;

    @FXML
    private JFXButton btnPayment;

    @FXML
    private Label lbl;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    void btnAppointmentManageOnAction(ActionEvent event) throws IOException {
//        Navigation.setAppointmentForm();
        AnchorPane rootpanea= FXMLLoader.load(getClass().getResource("/ik/ijse/studioclassiceye/view/appointmentForm.fxml"));
        pane2.getChildren().setAll(rootpanea);
    }

    @FXML
    void btnCusManageOnAction(ActionEvent event) throws IOException {
        AnchorPane rootpane= FXMLLoader.load(getClass().getResource("/ik/ijse/studioclassiceye/view/customerForm.fxml"));
        pane2.getChildren().setAll(rootpane);
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        AnchorPane rootpanepay= FXMLLoader.load(getClass().getResource("/ik/ijse/studioclassiceye/view/paymentsForm.fxml"));
        pane2.getChildren().setAll(rootpanepay);
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        AnchorPane rootpaneE= FXMLLoader.load(getClass().getResource("/ik/ijse/studioclassiceye/view/employeeForm.fxml"));
        pane2.getChildren().setAll(rootpaneE);
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

     /*void setLbl (){
        lbl.setText("Hashan");
    }*/

}
