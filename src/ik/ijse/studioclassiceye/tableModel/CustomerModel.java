package ik.ijse.studioclassiceye.tableModel;

import javafx.beans.property.SimpleStringProperty;

public class CustomerModel {
    private String cus_Id;
    private String name;
    private String address;
    private String contact;

    public String getCus_Id() {
        return cus_Id;
    }

    public void setCus_Id(String cus_Id) {
        this.cus_Id = cus_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public CustomerModel(String cus_Id, String name, String address, String contact) {
        this.cus_Id = new String(cus_Id);
        this.name = new String(name);
        this.address = new String(address);
        this.contact = new String(contact);
    }


}
