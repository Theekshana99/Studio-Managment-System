package ik.ijse.studioclassiceye.model;

import ik.ijse.studioclassiceye.db.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppointmentFormModel {
    public static ResultSet lableAppoimentId() throws SQLException, ClassNotFoundException {
        String sql="select A_Id from appointments order by A_Id desc limit 1";

        Statement stm = DBConnection.getDbConnection().getConnection().createStatement();

        ResultSet aId = stm.executeQuery(sql);
        String id= (String) aId.getObject(1);
        System.out.println(id);
        return aId;
    }
}
