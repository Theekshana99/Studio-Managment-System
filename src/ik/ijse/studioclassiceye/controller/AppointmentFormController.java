package ik.ijse.studioclassiceye.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import ik.ijse.studioclassiceye.db.DBConnection;
import ik.ijse.studioclassiceye.tableModel.AppointmentDetailModel;
import ik.ijse.studioclassiceye.tableModel.SupPayModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;


import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class AppointmentFormController {
    public void initialize() throws SQLException, ClassNotFoundException {
     setBId();
     setCusId();
     setPackageId();
     setProductCode();
     setDate();
     setCellValueFactorySup();
    }

    @FXML
    private TableColumn<?, ?> clmProCode;

    @FXML
    private TableColumn<?, ?> clmProName;

    @FXML
    private TableColumn<?, ?> clmProPrice;

    @FXML
    private TableColumn<AppointmentDetailModel, String> clmAppointmentId;

    @FXML
    private TableColumn<AppointmentDetailModel, String> clmPackageID;

    @FXML
    private TableColumn<AppointmentDetailModel, String> clmReqDate;

    @FXML
    private TableColumn<AppointmentDetailModel, String> clmPackagePrice;

    @FXML
    private JFXComboBox<?> cmbProductCode;

    @FXML
    private AnchorPane paneappoi;

    @FXML
    private Label lblAppointmentId;

    @FXML
    private Label lblAppoiDate;

    @FXML
    private JFXComboBox<?> cmbCusId;

    @FXML
    private Label lblCusName;

    @FXML
    private JFXComboBox<?> cmbPackageId;

    @FXML
    private Label lblAppoiDate11;

    @FXML
    private Label lblAppointmentDescription;

    @FXML
    private JFXButton btnAddPackage;

    @FXML
    private JFXButton btnClearPackage;

    @FXML
    private TableView<AppointmentDetailModel> tblPackage;

    @FXML
    private JFXButton btnAddProduct;

    @FXML
    private JFXButton btnClearProduct;

    @FXML
    private TableView<?> tblProduct;

    @FXML
    private Label lblAppoiTotal;

    @FXML
    private JFXButton btnPlaceAppoi;

    @FXML
    private Label lblAppointmentProName;

    @FXML
    private Label lblAppoiPrice;

    @FXML
    private Label lblPackagePrice;

    @FXML
    private JFXDatePicker dateReqDate;

    @FXML
    private JFXTextField txtAppoiProQty;

    @FXML
    private Label lblQtyOnHand;

    private ObservableList<AppointmentDetailModel> obList = FXCollections.observableArrayList();
    private void setCellValueFactorySup() {
        clmAppointmentId.setCellValueFactory(new PropertyValueFactory("AId"));
        clmPackageID.setCellValueFactory(new PropertyValueFactory("PId"));
        clmReqDate.setCellValueFactory(new PropertyValueFactory("reqDate"));
        clmPackagePrice.setCellValueFactory(new PropertyValueFactory("price"));
    }

    @FXML
    void btnAddPackageOnAction(ActionEvent event) {
        String AId=lblAppointmentId.getText();
        String PId= (String) cmbPackageId.getValue();
        String reqDate=lblAppoiDate.getText();
        String price=lblPackagePrice.getText();
        obList.add(new AppointmentDetailModel(AId,PId,reqDate,price));
        tblPackage.setItems(obList);
    }

    @FXML
    void btnReportOnAction(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/ik/ijse/studioclassiceye/reports/Studio1.jrxml");

        //HashMap is something like key-value pairing data storing mechanism
        HashMap<String, Object> hm = new HashMap<>();


        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            //if you haven't any parameters(HashMap reference) to pass, then put null for second argument/
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getDbConnection().getConnection());
//            JasperPrintManager.printReport(jasperPrint, true);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddProductOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearPackageOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearProductOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceAppoi(ActionEvent event) {

    }

    @FXML
    void cmbCusIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        setCustomerName();
    }

    @FXML
    void cmbPackageIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        setDescription();
    }

    @FXML
    void cmbProductCodeOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        setProductName();
    }

    @FXML
    void txtAppoiProQtyOnAction(ActionEvent event) {

    }

    public static String generateNextAppointmentId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT A_Id FROM appointments ORDER BY A_Id DESC LIMIT 1";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet result= stm.executeQuery(sql);
        if (result.next()) {
            return generateNextAppointmentId((result.getString(1)));
        }
        return generateNextAppointmentId("A001");
    }

    private static String generateNextAppointmentId(String currentOrderId) {
        if (currentOrderId.equals("A001")){
            return "A001";
        }
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("A00");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "A00" + id;
        }
        return "A001";
    }

    public void setBId() throws SQLException, ClassNotFoundException {
        String bid=generateNextAppointmentId();
        lblAppointmentId.setText(bid);
    }

    ObservableList cus_Id= FXCollections.observableArrayList();
    public void setCusId() throws SQLException, ClassNotFoundException {
        String sql="select cus_Id from customer";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);
        ArrayList<String> arrayList=new ArrayList();
        while(resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        for(String id:arrayList){
            cus_Id.add(id);
        }
        cmbCusId.setItems(cus_Id);
    }

    ObservableList package_id= FXCollections.observableArrayList();
    public void setPackageId() throws SQLException, ClassNotFoundException {
        String sql="select P_Id from packages";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);
        ArrayList<String> arrayList=new ArrayList();
        while(resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        for(String id:arrayList){
            package_id.add(id);
        }
        cmbPackageId.setItems(package_id);
    }

    public void setDescription() throws SQLException, ClassNotFoundException {
        String pId= (String) cmbPackageId.getValue();
        String sql="select description,price from packages where P_Id=?";
        PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
        pstm.setObject(1,pId);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            lblAppointmentDescription.setText(resultSet.getString(1));
            lblPackagePrice.setText(resultSet.getString(2));
        }
    }

    public void setCustomerName() throws SQLException, ClassNotFoundException {
        String cusId= (String) cmbCusId.getValue();
        String sql="select name from customer where cus_Id=?";
        PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
        pstm.setObject(1,cusId);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            lblCusName.setText(resultSet.getString(1));
        }
    }

    public void setProductName() throws SQLException, ClassNotFoundException {
        String proCode= (String) cmbProductCode.getValue();
        String sql="select name,unit_price from product where P_code=?";
        PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
        pstm.setObject(1,proCode);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            lblAppointmentProName.setText(resultSet.getString(1));
            lblAppoiPrice.setText(resultSet.getString(2));
        }
    }
    ObservableList productCode= FXCollections.observableArrayList();
    public void setProductCode() throws SQLException, ClassNotFoundException {
        String sql="select name from product";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);
        ArrayList<String> arrayList=new ArrayList();
        while(resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        for(String id:arrayList){
            productCode.add(id);
        }
        cmbProductCode.setItems(productCode);
    }

    public void setDate(){
        lblAppoiDate.setText(String.valueOf(LocalDate.now()));
    }
}


class A {
    private static A a;
    private A(){

    }

    public static A getInstance(){
        if (a==null){
            a=new A();
        }
        return a;
    }
}