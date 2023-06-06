package ik.ijse.studioclassiceye.model;

import ik.ijse.studioclassiceye.db.DBConnection;
import ik.ijse.studioclassiceye.to.Suppliers;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierFormModel {

    public int addsuppliers(Suppliers suppliers) throws SQLException, ClassNotFoundException {
        String sql="insert into suppliers values(?,?,?,?)";
        PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
        pstm.setObject(1,suppliers.getSupId());
        pstm.setObject(2,suppliers.getSupName());
        pstm.setObject(3,suppliers.getSupAddress());
        pstm.setObject(4,suppliers.getSupContact());
        int executeUpdate = pstm.executeUpdate();

        if (executeUpdate>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supplier Add Massage");
            alert.setHeaderText("Add the Supplier");
            alert.setContentText("Supplier Added Successfully!");
            alert.show();
        }else{
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Something Went Wrong");
            error.setContentText("Added Unsuccessfully");
            error.show();
        }
        return executeUpdate;
    }

    public void deleteSuppliers(Suppliers suppliers) throws SQLException, ClassNotFoundException {
        String sql="delete from suppliers where sup_Id=?";

        PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);

        pstm.setObject(1,suppliers.getSupId());

        int executeUpdate = pstm.executeUpdate();
        if (executeUpdate>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Delete Massage");
            alert.setHeaderText("Delete the Customer");
            alert.setContentText("Customer Deleted Successfully!");
            alert.show();
        }else{
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Something Went Wrong");
            error.setContentText("Deleted Unsuccessfully");
            error.show();
        }
    }

    public ResultSet searchSuppliers(Suppliers suppliers) throws SQLException, ClassNotFoundException {
        String sql="select * from suppliers where sup_Id=?";
        PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
        pstm.setObject(1,suppliers.getSupId());
        ResultSet resultSet = pstm.executeQuery();

        return resultSet;
    }

    public void suppliersUpdate(Suppliers suppliers) throws SQLException, ClassNotFoundException {
        String sql="update suppliers set name=?,address=?,cantact=? where Sup_Id=?";

        PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);

        pstm.setObject(1,suppliers.getSupName());
        pstm.setObject(2,suppliers.getSupAddress());
        pstm.setObject(3,suppliers.getSupContact());
        pstm.setObject(4,suppliers.getSupId());

        int executeUpdate = pstm.executeUpdate();

        if (executeUpdate>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("suppliers Update Massage");
            alert.setHeaderText("Update the Customer");
            alert.setContentText("suppliers Updated Successfully!");
            alert.show();
        }else{

            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Something Went Wrong");
            error.setContentText("Updated Unsuccessfully");
            error.show();
        }
    }
}
