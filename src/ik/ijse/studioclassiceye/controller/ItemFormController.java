package ik.ijse.studioclassiceye.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import ik.ijse.studioclassiceye.db.DBConnection;
import ik.ijse.studioclassiceye.model.ItemFormModel;
import ik.ijse.studioclassiceye.tableModel.ItemModel;
import ik.ijse.studioclassiceye.to.Item;
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

public class ItemFormController {

    @FXML
    private AnchorPane panecus;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtItemQty;

    @FXML
    private JFXButton btnItemAdd;

    @FXML
    private JFXButton btnItemUpdate;

    @FXML
    private JFXButton btnItemDelete;

    @FXML
    private TableView<ItemModel> tblItem;

    @FXML
    private TableColumn<?, ?> clmItemCode;

    @FXML
    private TableColumn<?, ?> clmItemName;

    @FXML
    private TableColumn<?, ?> clmItemQty;

    public void initialize() throws SQLException, ClassNotFoundException {

        clmItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        clmItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        clmItemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));
        setObList();
        tblItem.setItems(obList);
        txtItemCode.requestFocus();
    }

    private ObservableList<ItemModel> obList= FXCollections.observableArrayList();
    public ResultSet getTableValues() throws SQLException, ClassNotFoundException {
        String sql="select * from Item";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);
        return resultSet;
    }

    public ObservableList setObList() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = getTableValues();
        while(resultSet.next()) {
            obList.add(new ItemModel(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
        }
        for (int i=0;i<4;i++) {
            System.out.println();
        }
        return obList;
    }

    @FXML
    void btnItemDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String itemCode=txtItemCode.getText();
        if (itemCode.equals("")){
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Please input an item code for delete item");
            error.setContentText("Delete Unsuccessfully");
            error.show();
        }else {
            Item item = new Item(itemCode);
            new ItemFormModel().deleteItem(item);
        }
        obList.clear();
        setObList();
    }

    @FXML
    void btnItemAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String itemCode=txtItemCode.getText();
        String itemName=txtItemName.getText();
        String itemQty=txtItemQty.getText();

        if (itemCode.equals("")|itemName.equals("")|itemQty.equals("")){
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Some data fields are empty");
            error.setContentText("Added Unsuccessfully");
            error.show();
        }else {
            Item item = new Item(itemCode, itemName, itemQty);
            int i = new ItemFormModel().addItem(item);
            if (i>0){
                txtItemCode.clear();
                txtItemName.clear();
                txtItemQty.clear();
                txtItemCode.requestFocus();
            }
        }
        obList.clear();
        setObList();
    }

    @FXML
    void btnItemUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String itemCode=txtItemCode.getText();
        String itemName=txtItemName.getText();
        String itemQty=txtItemQty.getText();

        if (itemCode.equals("")|itemName.equals("")|itemQty.equals("")){
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Some data fields are empty");
            error.setContentText("Update Unsuccessfully");
            error.show();
        }else {
            Item item = new Item(itemCode, itemName, itemQty);
            new ItemFormModel().updateItem(item);
        }
        obList.clear();
        setObList();
    }

    @FXML
    void txtItemCodeOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String I_code=txtItemCode.getText();

        if (I_code.equals("")){

        }else {
            Item item=new Item(I_code);
            ResultSet resultSet=new ItemFormModel().searchItem(item);
            if (resultSet.next()) {
                txtItemName.setText(resultSet.getString(2));
                txtItemQty.setText(resultSet.getString(3));
            }else {
                txtItemName.requestFocus();
            }
        }
    }

    @FXML
    void txtItemNameOnAction(ActionEvent event) {
        txtItemQty.requestFocus();
    }

    @FXML
    void txtItemQtyOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String itemCode=txtItemCode.getText();
        String itemName=txtItemName.getText();
        String itemQty=txtItemQty.getText();

        if (itemCode.equals("")|itemName.equals("")|itemQty.equals("")){
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Some data fields are empty");
            error.setContentText("Added Unsuccessfully");
            error.show();
        }else {
            Item item = new Item(itemCode, itemName, itemQty);
            int i = new ItemFormModel().addItem(item);
            if (i>0){
                txtItemCode.clear();
                txtItemName.clear();
                txtItemQty.clear();
                txtItemCode.requestFocus();
            }
        }
        obList.clear();
        setObList();
    }

}
