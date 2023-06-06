package ik.ijse.studioclassiceye.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import ik.ijse.studioclassiceye.db.DBConnection;
import ik.ijse.studioclassiceye.model.PaymentFormModel;
import ik.ijse.studioclassiceye.tableModel.CustomerPaymentModel;
import ik.ijse.studioclassiceye.tableModel.SupPayModel;
import ik.ijse.studioclassiceye.to.BatchPayment;
import ik.ijse.studioclassiceye.to.Payment;
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
import java.time.LocalDate;
import java.util.ArrayList;

public class PaymentsFormController {
    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactoryCus();
        setCellValueFactorySup();
        setObList();
        tblCusPayment.setItems(obListCusPay);
        cmbAppoiId.setPromptText("set A_Id");
        setAppointmentId();
        setPId();
        loadPaymentDate();
        setBId();
        setItemCode();
        setSupplierId();

    }

    public void setCellValueFactoryCus(){
        clmPayId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        clmAppoiId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        clmPayDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        clmPayPrice.setCellValueFactory(new PropertyValueFactory<>("paymentPrice"));
    }

    private ObservableList<CustomerPaymentModel> obListCusPay= FXCollections.observableArrayList();
    public ResultSet getTableValues() throws SQLException, ClassNotFoundException {
        String sql="select * from customer_payments";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);
        return resultSet;
    }

    public ObservableList setObList() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = getTableValues();
        while(resultSet.next()) {
            obListCusPay.add(new CustomerPaymentModel(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)));
        }

        return obList;
    }

    @FXML
    private AnchorPane panecus;

    @FXML
    private JFXTextField txtPaymentPrice;

    @FXML
    private JFXButton btnCusPay;

    @FXML
    private Label lblPaymentId;

    @FXML
    private Label lblPaymentDate;

    @FXML
    private JFXComboBox<?> cmbAppoiId;

    @FXML
    private Label lblBatchId;

    @FXML
    private JFXComboBox<?> cmbItemCode;

    @FXML
    private JFXTextField txtItemQty;

    @FXML
    private JFXTextField txtBatchPrice;

    @FXML
    private JFXButton btnBatchPay;

    @FXML
    private Label lblTotalSupPay;

    @FXML
    private JFXComboBox<?> cmbSupId;

    @FXML
    private TableView<CustomerPaymentModel> tblCusPayment;


    @FXML
    private TableView<SupPayModel> tblSupPayment;

    @FXML
    private TableColumn<SupPayModel, String> batchId;

    @FXML
    private TableColumn<SupPayModel, String> supplierId;

    @FXML
    private TableColumn<SupPayModel, String> itemCode;

    @FXML
    private TableColumn<SupPayModel, String> qty;

    @FXML
    private TableColumn<SupPayModel, String> unitPrice;

    @FXML
    private JFXButton btnBatchadd;

    @FXML
    private TableColumn<CustomerPaymentModel, String> clmPayId;

    @FXML
    private TableColumn<CustomerPaymentModel, String> clmAppoiId;

    @FXML
    private TableColumn<CustomerPaymentModel, String> clmPayDate;

    @FXML
    private TableColumn<CustomerPaymentModel, String> clmPayPrice;


    @FXML
    void btnBatchPayOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ArrayList <SupPayModel> supPay=new ArrayList();
        String batchId=lblBatchId.getText();
        String supId= (String) cmbSupId.getValue();
        String date=lblPaymentDate.getText();

        for (int i=0;i < tblSupPayment.getItems().size(); i++){
            SupPayModel spm=obList.get(i);
            supPay.add(new SupPayModel(spm.getBatch_id(),spm.getI_code(),spm.getQty(),spm.getUnit_price()));
        }
        BatchPayment batchPayment=new BatchPayment(batchId,supId,date,total);
        boolean isAdded= PaymentFormModel.batchPayment(batchPayment,supPay);
        if (isAdded){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Payment Massage");
            alert.setHeaderText("Payment Success");
            alert.setContentText("Payment has Successfully!");
            alert.show();
        }else {
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Something Went Wrong");
            error.setContentText("Payment Unsuccessfully");
            error.show();
        }

    }

    private ObservableList<CustomerPaymentModel> obListCusPayRow= FXCollections.observableArrayList();
    public void addRowToCusPayTbl(){
        String pay_Id=lblPaymentId.getText();
        String appoi_Id= (String) cmbAppoiId.getValue();
        String pay_Date=lblPaymentDate.getText();
        String pay_Price=txtPaymentPrice.getText();

        obListCusPayRow.add(new CustomerPaymentModel(pay_Id,appoi_Id,pay_Date,pay_Price));
        tblCusPayment.setItems(obListCusPayRow);
    }
    @FXML
    void btnCusPayOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String pay_Id=lblPaymentId.getText();
        String appoi_Id= (String) cmbAppoiId.getValue();
        String pay_Date=lblPaymentDate.getText();
        String pay_Price=txtPaymentPrice.getText();
        if (pay_Price.equals("")){
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Please input an amount to pay!");
            error.setContentText("Payment Unsuccessfully");
            error.show();
        }else{
        Payment payment=new Payment(pay_Id,appoi_Id,pay_Date,pay_Price);
            int i = new PaymentFormModel().customerPayment(payment);
            if (i>0){
                cmbAppoiId.setPromptText("set A_Id");
                txtPaymentPrice.clear();
                setPId();
                obListCusPay.clear();
                setObList();
            }
        }
    }

    @FXML
    void cmbAppoiIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbItemCodeOnAction(ActionEvent event) {

    }

    private ObservableList A_Id= FXCollections.observableArrayList();
    public void setAppointmentId() throws SQLException, ClassNotFoundException {
        String sql="select A_Id from appointments";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);
        ArrayList<String> arrayList=new ArrayList();
        while(resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        for(String id:arrayList){
            A_Id.add(id);
        }
        cmbAppoiId.setItems(A_Id);

    }

    public static String generateNextPaymentId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT pay_Id FROM customer_payments ORDER BY pay_Id DESC LIMIT 1";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet result= stm.executeQuery(sql);
        if (result.next()) {
            return generateNextPaymentId(result.getString(1));
        }
        return generateNextPaymentId("P01");
    }

    private static String generateNextPaymentId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("P0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "P0" + id;
        }
        if (currentOrderId.equals("P01")){
            return "P01";
        }
        return "P01";
    }

    private void loadPaymentDate() {
        lblPaymentDate.setText(String.valueOf(LocalDate.now()));
    }

    public void setPId() throws SQLException, ClassNotFoundException {
        String pid=generateNextPaymentId();
        lblPaymentId.setText(pid);
    }

    public static String generateNextBatchId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT batch_Id FROM sup_batch ORDER BY batch_Id DESC LIMIT 1";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet result= stm.executeQuery(sql);
        if (result.next()) {
            return generateNextBatchId(result.getString(1));
        }
        return generateNextBatchId(result.getString(null));
    }

    private static String generateNextBatchId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("B00");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "B00" + id;
        }
        return "P001";
    }

    public void setBId() throws SQLException, ClassNotFoundException {
        String bid=generateNextBatchId();
        lblBatchId.setText(bid);
    }
    ObservableList I_code= FXCollections.observableArrayList();
    public void setItemCode() throws SQLException, ClassNotFoundException {
        String sql="select I_Code from item";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);
        ArrayList<String> arrayList=new ArrayList();
        while(resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        for(String id:arrayList){
            I_code.add(id);
        }
        cmbItemCode.setItems(I_code);
    }

    ObservableList S_Id= FXCollections.observableArrayList();
    public void setSupplierId() throws SQLException, ClassNotFoundException {
        String sql="select Sup_Id from suppliers";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);
        ArrayList<String> arrayList=new ArrayList();
        while(resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        for(String id:arrayList){
            S_Id.add(id);
        }
        cmbSupId.setItems(S_Id);
    }
    private ObservableList<SupPayModel> obList = FXCollections.observableArrayList();
    private void setCellValueFactorySup() {
        batchId.setCellValueFactory(new PropertyValueFactory("batch_id"));
        supplierId.setCellValueFactory(new PropertyValueFactory("sup_id"));
        itemCode.setCellValueFactory(new PropertyValueFactory("i_code"));
        qty.setCellValueFactory(new PropertyValueFactory("qty"));
        unitPrice.setCellValueFactory(new PropertyValueFactory("unit_price"));

    }
    double total;
    @FXML
    void btnBatchAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String batchId=lblBatchId.getText();
        String itemCode= (String) cmbItemCode.getValue();
        String supId= (String) cmbSupId.getValue();
        String qty= txtItemQty.getText();
        String price=txtBatchPrice.getText();
        String date=lblPaymentDate.getText();
        double p= Double.parseDouble(price);
        total+=getTotal(p,total);

        obList.add(new SupPayModel(batchId,itemCode,supId,qty,price));
        tblSupPayment.setItems(obList);
        lblTotalSupPay.setText(String.valueOf(total));

    }

    private double getTotal(double price, double total) {
        total=price;
        return total;
    }


}
