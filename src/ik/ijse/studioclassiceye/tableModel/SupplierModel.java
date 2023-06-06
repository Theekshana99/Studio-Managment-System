package ik.ijse.studioclassiceye.tableModel;

public class SupplierModel {
   private String supId;
   private String supName;
   private String supAddress;
   private String supContact;

    public SupplierModel(String supId, String supNmae, String supAddress, String supContact) {
        this.supId = supId;
        this.supName = supNmae;
        this.supAddress = supAddress;
        this.supContact = supContact;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getSupAddress() {
        return supAddress;
    }

    public void setSupAddress(String supAddress) {
        this.supAddress = supAddress;
    }

    public String getSupContact() {
        return supContact;
    }

    public void setSupContact(String supContact) {
        this.supContact = supContact;
    }
}
