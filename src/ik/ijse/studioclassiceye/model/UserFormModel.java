package ik.ijse.studioclassiceye.model;

import ik.ijse.studioclassiceye.db.DBConnection;
import ik.ijse.studioclassiceye.to.User;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserFormModel {

    public int addUser(User user) throws SQLException, ClassNotFoundException {
        String sql="insert into user values(?,?,?,?,?)";
        PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
        pstm.setObject(1,user.getUserName());
        pstm.setObject(2,user.getEmpId());
        pstm.setObject(3,user.getPassword());
        pstm.setObject(4,user.getDateOfBirth());
        pstm.setObject(5,user.getRole());
        int i = pstm.executeUpdate();
        if (i>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User Add Massage");
            alert.setHeaderText("Add the User");
            alert.setContentText("User Added Successfully!");
            alert.show();
        }else {
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Something Went Wrong");
            error.setContentText("Added Unsuccessfully");
            error.show();
        }
        return i;
    }

    public int updateUser(User user) throws SQLException, ClassNotFoundException {
        String sql="update user set emp_Id=?,password=?,date_Of_Birth=?,role=? where user_Name=?";
        PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);

        pstm.setObject(1,user.getEmpId());
        pstm.setObject(2,user.getPassword());
        pstm.setObject(3,user.getDateOfBirth());
        pstm.setObject(4,user.getRole());
        pstm.setObject(5,user.getUserName());
        int i = pstm.executeUpdate();
        if (i>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User Update Massage");
            alert.setHeaderText("Update the User");
            alert.setContentText("User Update Successfully!");
            alert.show();
        }else {
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Something Went Wrong");
            error.setContentText("Update Unsuccessfully");
            error.show();
        }
        return i;
    }

    public void deleteUser(User user) throws SQLException, ClassNotFoundException {
        String sql="delete from user where user_name=?";

        PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);

        pstm.setObject(1,user.getUserName());

        int executeUpdate = pstm.executeUpdate();
        if (executeUpdate>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User Delete Massage");
            alert.setHeaderText("User the Customer");
            alert.setContentText("Customer User Successfully!");
            alert.show();
        }else{
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Something Went Wrong");
            error.setContentText("Deleted Unsuccessfully");
            error.show();
        }
    }
}
