package ik.ijse.studioclassiceye.model;

import ik.ijse.studioclassiceye.db.DBConnection;
import ik.ijse.studioclassiceye.tableModel.ProductItemModel;
import ik.ijse.studioclassiceye.to.ProductTrance;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductFormModel {

    public static void addAppoiProDetails(ArrayList<ProductItemModel> proItem) throws SQLException, ClassNotFoundException {
        for (ProductItemModel pim : proItem) {
            String sql = "INSERT INTO appointment_product_details VALUES(?, ?, ?)";
            PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
//            pstm.setObject(1,pim.getProductCode());
//            pstm.setObject(2,pim.getItemCode());
//            pstm.setObject(3,pim.getQty());
//            System.out.println(pim.getProductCode());
//            System.out.println(pim.getItemCode());
//            System.out.println(pim.getQty());
            if (!save(pim)) {
            }
//            boolean save = save(pim);
//            System.out.println(save);
        }

    }

    private static boolean save(ProductItemModel pim) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO product_item values(?,?,?)";
        PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
        pstm.setObject(1,pim.getProductCode());
        pstm.setObject(2,pim.getItemCode());
        pstm.setObject(3,pim.getQty());
//        int i = pstm.executeUpdate();
        System.out.println(pim.getProductCode());
        System.out.println(pim.getItemCode());
        System.out.println(pim.getQty());

//        if (i>0){
//            return true;
//        }
        return true;
    }

    public static boolean addProduct(ProductTrance productTrance, ArrayList<ProductItemModel> proItem) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            String sql = "insert into product values(?,?,?)";
            PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
            pstm.setObject(1, productTrance.getPCode());
            pstm.setObject(2, productTrance.getName());
            pstm.setObject(3, productTrance.getUnitPrice());
            int i = pstm.executeUpdate();
            if (i > 0) {
                boolean addProductItem = addProductItem(proItem);
                if (addProductItem) {
                    boolean updateItem = updateItem(proItem);
                    if (updateItem){
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

    private static boolean updateItem(ArrayList<ProductItemModel> proItem) throws SQLException, ClassNotFoundException {
        String sql="update item set QtyOnHand= QtyOnHand-? where I_Code=?";
        int i=0;
        for (ProductItemModel p: proItem){
            PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
            pstm.setObject(1, p.getQty());
            pstm.setObject(2, p.getItemCode());
            int executeUpdate = pstm.executeUpdate();
            if (executeUpdate>0){
                i++;
            }
        }
        if (i== proItem.size()){
            return true;
        }

        return false;

    }

    private static boolean addProductItem(ArrayList<ProductItemModel> proItem) throws SQLException, ClassNotFoundException {
        String sql = "insert into product_item values(?,?,?)";
        int i=0;
        for (ProductItemModel p: proItem){
            PreparedStatement pstm = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
            pstm.setObject(1, p.getProductCode());
            pstm.setObject(2, p.getItemCode());
            pstm.setObject(3, p.getQty());
            int executeUpdate = pstm.executeUpdate();
            if (executeUpdate>0){
                i++;
            }
        }
        if (i== proItem.size()){
            return true;
        }

        return false;
    }
}
