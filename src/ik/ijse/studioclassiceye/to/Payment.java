package ik.ijse.studioclassiceye.to;

public class Payment {
    private String pay_Id;
    private String appoi_Id;
    private String pay_Date;
    private String price;

    public Payment(String pay_Id, String appoi_Id, String pay_Date, String price) {
        this.pay_Id = pay_Id;
        this.appoi_Id = appoi_Id;
        this.pay_Date = pay_Date;
        this.price = price;
    }

    public String getPay_Id() {
        return pay_Id;
    }

    public void setPay_Id(String pay_Id) {
        this.pay_Id = pay_Id;
    }

    public String getAppoi_Id() {
        return appoi_Id;
    }

    public void setAppoi_Id(String appoi_Id) {
        this.appoi_Id = appoi_Id;
    }

    public String getPay_Date() {
        return pay_Date;
    }

    public void setPay_Date(String pay_Date) {
        this.pay_Date = pay_Date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
