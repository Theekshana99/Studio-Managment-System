package ik.ijse.studioclassiceye.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
       connection= DriverManager.getConnection("jdbc:mysql://localhost/studio","root","1234");
    }
    public Connection getConnection(){
        return connection;
    }
    public static DBConnection getDbConnection() throws SQLException, ClassNotFoundException {
        if(dbConnection==null){
            dbConnection=new DBConnection();
            return dbConnection;
        }
        return dbConnection;
    }
}
