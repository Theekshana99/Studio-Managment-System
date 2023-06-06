package ik.ijse.studioclassiceye.model;

import ik.ijse.studioclassiceye.db.DBConnection;
import ik.ijse.studioclassiceye.to.Item;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemFormModel {

        public int addItem(Item item) throws SQLException, ClassNotFoundException {
            String sql="Insert into item values(?,?,?)";


            Connection connection= DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);

            Integer i;
            pstm.setObject(1,item.getItemCode());
            pstm.setObject(2,item.getItemName());
            pstm.setObject(3,i=Integer.parseInt(item.getItemQty()));

            int executeUpdate = pstm.executeUpdate();
            if (executeUpdate>0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("item Add Massage");
                alert.setHeaderText("Add the Customer");
                alert.setContentText("item Added Successfully!");
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



        public void updateItem(Item item) throws SQLException, ClassNotFoundException {
            String sql="update item set name=?,QtyOnHand=? where I_Code=?";


            Connection connection= DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);

            Integer i;
            pstm.setObject(1,item.getItemName());
            pstm.setObject(2,i=Integer.parseInt(item.getItemQty()));
            pstm.setObject(3,item.getItemCode());

            int executeUpdate = pstm.executeUpdate();
            if (executeUpdate>0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("item Update Massage");
                alert.setHeaderText("Update the Customer");
                alert.setContentText("item Update Successfully!");
                alert.show();
            }else{
                Alert error=new Alert(Alert.AlertType.WARNING);
                error.setTitle("Error");
                error.setHeaderText("Something Went Wrong");
                error.setContentText("Update Unsuccessfully");
                error.show();
            }
        }



        public void deleteItem(Item item) throws SQLException, ClassNotFoundException {
            String sql="delete from item where I_Code=?";

            PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);

            pstm.setObject(1,item.getItemCode());

            int executeUpdate = pstm.executeUpdate();
            if (executeUpdate>0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Item Delete Massage");
                alert.setHeaderText("Delete the item");
                alert.setContentText("Item Deleted Successfully!");
                alert.show();
            }else{
                Alert error=new Alert(Alert.AlertType.WARNING);
                error.setTitle("Error");
                error.setHeaderText("Something Went Wrong");
                error.setContentText("Deleted Unsuccessfully");
                error.show();
            }
        }



        public ResultSet searchItem(Item item) throws SQLException, ClassNotFoundException {
            String sql="select * from item where I_Code=?";

            PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
            pstm.setObject(1,item.getItemCode());
            ResultSet resultSet = pstm.executeQuery();
            return resultSet;
        }

}
