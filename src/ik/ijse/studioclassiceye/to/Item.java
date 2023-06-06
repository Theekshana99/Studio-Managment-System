package ik.ijse.studioclassiceye.to;

public class Item {
    String itemCode;
    String itemName;
    String itemQty;

    public Item(String itemCode) {
        this.itemCode = itemCode;
    }

    public Item(String itemCode, String itemName, String itemQty) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemQty = itemQty;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemQty() {
        return itemQty;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }
}
