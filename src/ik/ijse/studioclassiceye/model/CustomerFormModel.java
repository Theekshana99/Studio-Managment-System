package ik.ijse.studioclassiceye.model;

import com.sun.nio.sctp.Notification;
import ik.ijse.studioclassiceye.db.DBConnection;
import ik.ijse.studioclassiceye.to.Customer;
import ik.ijse.studioclassiceye.to.Suppliers;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

import java.sql.*;

public class CustomerFormModel {
        public int customerAdd (Customer customer) throws SQLException, ClassNotFoundException {
                String sql="Insert into customer values(?,?,?,?)";


                Connection connection=DBConnection.getDbConnection().getConnection();
                PreparedStatement pstm = connection.prepareStatement(sql);

                pstm.setObject(1,customer.getId());
                pstm.setObject(2,customer.getName());
                pstm.setObject(3,customer.getAddress());
                pstm.setObject(4,customer.getContact());

                int executeUpdate = pstm.executeUpdate();
                if (executeUpdate>0){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Customer Add Massage");
                        alert.setHeaderText("Add the Customer");
                        alert.setContentText("Customer Added Successfully!");
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

        public void customerDelete(Customer customer) throws SQLException, ClassNotFoundException {
                String sql="delete from customer where cus_Id=?";

                PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);

                pstm.setObject(1,customer.getId());

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

        public void customerUpdate(Customer customer) throws SQLException, ClassNotFoundException {
                String sql="update customer set name=?,address=?,contact=? where cus_Id=?";

                PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);

                pstm.setObject(1,customer.getName());
                pstm.setObject(2,customer.getAddress());
                pstm.setObject(3,customer.getContact());
                pstm.setObject(4,customer.getId());

                int executeUpdate = pstm.executeUpdate();

                if (executeUpdate>0){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Customer Update Massage");
                        alert.setHeaderText("Update the Customer");
                        alert.setContentText("Customer Updated Successfully!");
                        alert.show();
                }else{

                        Alert error=new Alert(Alert.AlertType.WARNING);
                        error.setTitle("Error");
                        error.setHeaderText("Something Went Wrong");
                        error.setContentText("Updated Unsuccessfully");
                        error.show();
                }
        }

        public void showTable(TableView<?> tblCustomer) throws SQLException, ClassNotFoundException {
//                String sql="Select * from customer";
//                Statement stm = DBConnection.getDbConnection().getConnection().createStatement();
//                ResultSet executeQuery = stm.executeQuery(sql);




        }

        public ResultSet searchCustomer(Customer customer) throws SQLException, ClassNotFoundException {
                String sql="select * from customer where cus_Id=?";
                PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
                pstm.setObject(1,customer.getId());
                ResultSet resultSet = pstm.executeQuery();

                return resultSet;

        }


}
