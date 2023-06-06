package ik.ijse.studioclassiceye.tableModel;

public class ProductItemModel {
    private String productCode;
    private String itemCode;
    private String qty;

    public ProductItemModel(String productCode, String itemCode, String qty) {
        this.productCode = productCode;
        this.itemCode = itemCode;
        this.qty = qty;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

}
