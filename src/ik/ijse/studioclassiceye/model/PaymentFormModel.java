package ik.ijse.studioclassiceye.model;

import ik.ijse.studioclassiceye.db.DBConnection;
import ik.ijse.studioclassiceye.tableModel.SupPayModel;
import ik.ijse.studioclassiceye.to.BatchPayment;
import ik.ijse.studioclassiceye.to.Payment;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentFormModel {

    public int customerPayment(Payment payment) throws SQLException, ClassNotFoundException {
        String sql="insert into customer_payments values(?,?,?,?)";
        PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
        pstm.setObject(1,payment.getPay_Id());
        pstm.setObject(2,payment.getAppoi_Id());
        pstm.setObject(3,payment.getPay_Date());
        pstm.setObject(4,payment.getPrice());
        int executeUpdate = pstm.executeUpdate();
        if (executeUpdate>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Payment Massage");
            alert.setHeaderText("Payed");
            alert.setContentText("Customer Payment Successfully!");
            alert.show();
        }
        return executeUpdate;
    }

    public static boolean batchPayment(BatchPayment batchPayment, ArrayList<SupPayModel> supPay) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            String sql = "insert into sup_batch values(?,?,?,?)";

            PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
            pstm.setObject(1, batchPayment.getBatchId());
            pstm.setObject(2, batchPayment.getSupId());
            pstm.setObject(3, batchPayment.getDate());
            pstm.setObject(4, batchPayment.getCost());
            int i = pstm.executeUpdate();
            if (i > 0) {
                boolean isaddsupBatchPayment = addSupBatchPayment(supPay);
                if (isaddsupBatchPayment) {
                    boolean isUpdateItem=updateItem(supPay);
                    if (isUpdateItem){
                        DBConnection.getDbConnection().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getDbConnection().getConnection().rollback();
            return false;
        }finally {
            DBConnection.getDbConnection().getConnection().setAutoCommit(true);
        }

    }

    private static boolean updateItem(ArrayList<SupPayModel> supPay) throws SQLException, ClassNotFoundException {
        int i=0;
        String sql="update item set QtyOnHand=QtyOnHand+? where I_Code=?";
        for (SupPayModel s:supPay) {
            PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
            pstm.setObject(1, s.getQty());
            pstm.setObject(2, s.getI_code());
            int executeUpdate = pstm.executeUpdate();
            if (executeUpdate>0){
                i++;
            }
        }
        if (i== supPay.size()){
            return true;
        }
        return false;
    }

    private static boolean addSupBatchPayment(ArrayList<SupPayModel> supPay) throws SQLException {
        int i=0;
        for (SupPayModel s: supPay) {
            String sql = "insert into sup_batch_payment values(?,?,?,?)";

            PreparedStatement pstm = null;
            try {
                pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
                pstm.setObject(1, s.getBatch_id());
                pstm.setObject(2, s.getI_code());
                pstm.setObject(3, Integer.parseInt(s.getQty()));
                pstm.setObject(4, Double.parseDouble(s.getUnit_price()));
                int executeUpdate = pstm.executeUpdate();
                if (executeUpdate > 0) {
                    i++;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (i == supPay.size()) {
                return true;
            }
        }


        return false;
    }
}
