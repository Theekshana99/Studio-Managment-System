package ik.ijse.studioclassiceye.tableModel;

public class ProductModel {
    private String productCode;
    private String productName;
    private String unitPrice;

    public ProductModel(String productCode, String productName, String unitPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.unitPrice = unitPrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
}
