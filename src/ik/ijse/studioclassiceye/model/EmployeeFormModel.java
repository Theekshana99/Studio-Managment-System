package ik.ijse.studioclassiceye.model;

import ik.ijse.studioclassiceye.db.DBConnection;
import ik.ijse.studioclassiceye.to.Employee;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeFormModel {
    public int employeeAdd(Employee employee) throws SQLException, ClassNotFoundException {
        String sql="Insert into employee values(?,?,?,?,?,?)";


        Connection connection= DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1,employee.getEmpId());
        pstm.setObject(2,employee.getEmpName());
        pstm.setObject(3,employee.getEmpAddress());
        pstm.setObject(4,employee.getEmpContact());
        Double d=Double.parseDouble(employee.getEmpSalary());
        pstm.setObject(5,d);
        pstm.setObject(6,employee.getRole());

        int executeUpdate = pstm.executeUpdate();
        if (executeUpdate>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("employee Add Massage");
            alert.setHeaderText("Add the employee");
            alert.setContentText("employee Added Successfully!");
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

    public void employeeUpdate(Employee employee) throws SQLException, ClassNotFoundException {
        String sql="update employee set emp_Id=?,name=?,address=?,contact=?,salary=?,role=?";

        Connection connection= DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1,employee.getEmpId());
        pstm.setObject(2,employee.getEmpName());
        pstm.setObject(3,employee.getEmpAddress());
        pstm.setObject(4,employee.getEmpContact());
        Double d=Double.parseDouble(employee.getEmpSalary());
        pstm.setObject(5,d);
        pstm.setObject(6,employee.getRole());

        int executeUpdate = pstm.executeUpdate();
        if (executeUpdate>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("employee Update Massage");
            alert.setHeaderText("Update the employee");
            alert.setContentText("employee Updated Successfully!");
            alert.show();
        }else{
            Alert error=new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Something Went Wrong");
            error.setContentText("Update Unsuccessfully");
            error.show();
        }
    }

    public ResultSet searchEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        String sql="select * from employee where emp_Id=?";
        PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
        pstm.setObject(1,employee.getEmpId());
        ResultSet resultSet = pstm.executeQuery();

        return resultSet;
    }

    public boolean deleteEmployee(String empId) throws SQLException, ClassNotFoundException {
        String sql="delete from employee where emp_Id=?";

        PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);

        pstm.setObject(1,empId);

        int executeUpdate = pstm.executeUpdate();
        if (executeUpdate>0){
            return true;

        }
        return false;
    }
}
