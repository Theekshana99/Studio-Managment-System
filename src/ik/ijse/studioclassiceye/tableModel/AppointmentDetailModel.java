package ik.ijse.studioclassiceye.tableModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AppointmentDetailModel {
    private String AId;
    private String PId;
    private String reqDate;
    private String price;

    public AppointmentDetailModel(String AId, String PId, String reqDate, String price) {
        this.AId = AId;
        this.PId = PId;
        this.reqDate = reqDate;
        this.price = price;
    }



    public String getAId() {
        return AId;
    }

    public void setAId(String AId) {
        this.AId = AId;
    }

    public String getPId() {
        return PId;
    }

    public void setPId(String PId) {
        this.PId = PId;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
