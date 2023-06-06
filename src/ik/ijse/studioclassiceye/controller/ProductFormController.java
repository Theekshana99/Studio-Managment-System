package ik.ijse.studioclassiceye.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import ik.ijse.studioclassiceye.db.DBConnection;
import ik.ijse.studioclassiceye.model.ProductFormModel;
import ik.ijse.studioclassiceye.tableModel.ProductItemModel;
import ik.ijse.studioclassiceye.tableModel.ProductModel;
import ik.ijse.studioclassiceye.to.ProductTrance;
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

public class ProductFormController {
    public void initialize() throws SQLException, ClassNotFoundException {
        setPCode();
        setItemCode();
        setCellValueFactory();
        setCellValueFactoryPro();
        setObList();
    }

    @FXML
    private AnchorPane panecus;

    @FXML
    private JFXTextField txtProductDescription;

    @FXML
    private JFXTextField txtProductPrice;

    @FXML
    private JFXButton btnProductAdd;

    @FXML
    private JFXButton btnProductUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXComboBox<?> cmbICode;

    @FXML
    private JFXTextField txtIQty;

    @FXML
    private TableView<ProductModel> tblProduct;

    @FXML
    private TableColumn<ProductItemModel, String> clmProItemPCode;

    @FXML
    private TableColumn<ProductItemModel, String> clmItemCode;

    @FXML
    private TableColumn<ProductItemModel, String> clmQty;

    @FXML
    private JFXButton btnAddItem;

    @FXML
    private TableView<ProductItemModel> tblProductItem;

    @FXML
    private TableColumn<ProductModel,String> clmProductPCode;

    @FXML
    private TableColumn<ProductModel,String> clmName;

    @FXML
    private TableColumn<ProductModel,String> clmUnitPrice;

    @FXML
    private Label lblProductCode;

    private ObservableList<ProductItemModel> obList = FXCollections.observableArrayList();

    private void setCellValueFactory() {
        clmProItemPCode.setCellValueFactory(new PropertyValueFactory("productCode"));
        clmItemCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        clmQty.setCellValueFactory(new PropertyValueFactory("qty"));
    }

    @FXML
    void btnAddItemeOnAction(ActionEvent event) {

        String productCode=lblProductCode.getText();
        String itemCode= (String) cmbICode.getValue();
        String qty=txtIQty.getText();

        obList.add(new ProductItemModel(productCode,itemCode,qty));
        tblProductItem.setItems(obList);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnProductAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ArrayList <ProductItemModel> proItem=new ArrayList();

        for (int i=0;i < tblProductItem.getItems().size(); i++){
            ProductItemModel ptm=obList.get(i);
            proItem.add(new ProductItemModel(ptm.getProductCode(),ptm.getItemCode(),ptm.getQty()));
        }

        ProductTrance productTrance=new ProductTrance(lblProductCode.getText(),txtProductDescription.getText(),txtProductPrice.getText());
        boolean addProduct = ProductFormModel.addProduct(productTrance, proItem);
        if (addProduct){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Massage");
            alert.setHeaderText("Add the Product");
            alert.setContentText("Product Added Successfully!");
            alert.show();
        }else {
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Something Went Wrong");
            error.setContentText("Added Unsuccessfully");
            error.show();
        }
        obList.clear();
        setObList();

    }

    @FXML
    void btnProductUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

    }


    @FXML
    void cmbICodeOnAction(ActionEvent event) {

    }

    @FXML
    void txtProductPriceOnAction(ActionEvent event) {

    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

    public void setPCode() throws SQLException, ClassNotFoundException {
        String pid=generateNextProductCode();
        lblProductCode.setText(pid);
    }

    public static String generateNextProductCode() throws SQLException, ClassNotFoundException {
        String sql = "SELECT P_Code FROM product ORDER BY P_Code DESC LIMIT 1";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet result= stm.executeQuery(sql);
        if (result.next()) {
            return generateNextBatchId(result.getString(1));
        }
        return generateNextBatchId("P01");
    }

    private static String generateNextBatchId(String currentOrderId) {
        if (currentOrderId.equals("P01")){
            return "P01";
        }
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("P0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "P0" + id;
        }

        return "P01";
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
        cmbICode.setItems(I_code);
    }

    public void setCellValueFactoryPro(){
        clmProductPCode.setCellValueFactory(new PropertyValueFactory<>("productCode"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        clmUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
    }

    private ObservableList<ProductModel> obListPro= FXCollections.observableArrayList();
    public ResultSet getTableValues() throws SQLException, ClassNotFoundException {
        String sql="select * from product";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);
        return resultSet;
    }

    public ObservableList setObList() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = getTableValues();
        while(resultSet.next()) {

            obListPro.add(new ProductModel(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
            tblProduct.setItems(obListPro);
        }
        return obList;
    }
}
