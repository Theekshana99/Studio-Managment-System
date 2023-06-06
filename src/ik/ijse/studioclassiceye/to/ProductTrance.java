package ik.ijse.studioclassiceye.to;

public class ProductTrance {
    private String PCode;
    private String name;
    private String unitPrice;
    private String itemCode;
    private String qty;
    private String qtyOnHand;

    public ProductTrance(String PCode, String name, String unitPrice, String itemCode, String qty, String qtyOnHand) {
        this.PCode = PCode;
        this.name = name;
        this.unitPrice = unitPrice;
        this.itemCode = itemCode;
        this.qty = qty;
        this.qtyOnHand = qtyOnHand;
    }

    public ProductTrance(String PCode, String name, String unitPrice) {
        this.PCode = PCode;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public ProductTrance() {
    }

    public String getPCode() {
        return PCode;
    }

    public void setPCode(String PCode) {
        this.PCode = PCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
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

    public String getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(String qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }
}
