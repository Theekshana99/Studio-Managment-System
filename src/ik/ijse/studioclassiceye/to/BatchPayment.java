package ik.ijse.studioclassiceye.to;

public class BatchPayment {
    private String batchId;
    private String supId;
    private String date;
    private Double cost;

    public BatchPayment(String batchId, String supId, String date, Double cost) {
        this.batchId = batchId;
        this.supId = supId;
        this.date = date;
        this.cost = cost;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
