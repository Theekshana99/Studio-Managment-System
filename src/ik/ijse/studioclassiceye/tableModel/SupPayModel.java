package ik.ijse.studioclassiceye.tableModel;

public class SupPayModel {
    private String batch_id;
    private String sup_id;
    private String i_code;
    private String qty;
    private String unit_price;

    public SupPayModel(String batch_id, String i_code, String qty, String unit_price) {
        this.batch_id = batch_id;
        this.i_code = i_code;
        this.qty = qty;
        this.unit_price = unit_price;
    }

    public SupPayModel(String batch_id, String sup_id, String i_code, String qty, String unit_price) {
        this.batch_id = batch_id;
        this.sup_id = sup_id;
        this.i_code = i_code;
        this.qty = qty;
        this.unit_price = unit_price;
    }


    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public String getSup_id() {
        return sup_id;
    }

    public void setSup_id(String sup_id) {
        this.sup_id = sup_id;
    }

    public String getI_code() {
        return i_code;
    }

    public void setI_code(String i_code) {
        this.i_code = i_code;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }
}
