package ik.ijse.studioclassiceye.model;

import ik.ijse.studioclassiceye.db.DBConnection;
import ik.ijse.studioclassiceye.to.Package;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageFormModel {

    public ResultSet searchPackages(Package packages) throws SQLException, ClassNotFoundException {
        String sql="select * from package where P_Id=?";
        PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
        pstm.setObject(1,packages.getPackageId());
        ResultSet resultSet = pstm.executeQuery();
        return resultSet;
    }

    public int updatePackage(Package aPackage) throws SQLException, ClassNotFoundException {
        String sql="update packages set description=?,price=? where P_Id=?";

        PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);

        pstm.setObject(1,aPackage.getPackageId());
        pstm.setObject(2,aPackage.getPackageDescription());
        pstm.setObject(3,aPackage.getPackagePrice());


        int executeUpdate = pstm.executeUpdate();

        if (executeUpdate>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Package Update Massage");
            alert.setHeaderText("Update the Package");
            alert.setContentText("Package Updated Successfully!");
            alert.show();
        }else{

            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Something Went Wrong");
            error.setContentText("Updated Unsuccessfully");
            error.show();
        }
        return executeUpdate;
    }

    public int packageDelete(Package packages) throws SQLException, ClassNotFoundException {
        String sql="delete from packages where cus_Id=?";

        PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);

        pstm.setObject(1,packages.getPackageId());

        int executeUpdate = pstm.executeUpdate();
        if (executeUpdate>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Package Delete Massage");
            alert.setHeaderText("Delete the Package");
            alert.setContentText("Package Deleted Successfully!");
            alert.show();
        }else{
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Something Went Wrong");
            error.setContentText("Deleted Unsuccessfully");
            error.show();
        }
        return executeUpdate;
    }

    public int addPackage(Package packages) throws SQLException, ClassNotFoundException {
        String sql="Insert into packages values(?,?,?)";


        Connection connection=DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1,packages.getPackageId());
        pstm.setObject(2,packages.getPackageDescription());
        pstm.setObject(3,packages.getPackagePrice());

        int executeUpdate = pstm.executeUpdate();
        if (executeUpdate>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Package Add Massage");
            alert.setHeaderText("Add the Package");
            alert.setContentText("Package Added Successfully!");
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
}
