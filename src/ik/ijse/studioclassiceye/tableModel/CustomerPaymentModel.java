package ik.ijse.studioclassiceye.tableModel;

public class CustomerPaymentModel {
    private String paymentId;
    private String appointmentId;
    private String paymentDate;
    private String paymentPrice;

    public CustomerPaymentModel(String paymentId, String appointmentId, String paymentDate, String paymentPrice) {
        this.paymentId = paymentId;
        this.appointmentId = appointmentId;
        this.paymentDate = paymentDate;
        this.paymentPrice = paymentPrice;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(String paymentPrice) {
        this.paymentPrice = paymentPrice;
    }
}
