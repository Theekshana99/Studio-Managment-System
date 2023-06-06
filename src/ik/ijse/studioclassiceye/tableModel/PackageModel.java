package ik.ijse.studioclassiceye.tableModel;

public class PackageModel {
    private String packageId;
    private String packageDescription;
    private String packagePrice;

    public PackageModel(String packageId) {
        this.packageId = packageId;
    }

    public PackageModel(String packageId, String packageDescription, String packagePrice) {
        this.packageId = packageId;
        this.packageDescription = packageDescription;
        this.packagePrice = packagePrice;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageDescription() {
        return packageDescription;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    public String getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(String packagPrice) {
        this.packagePrice = packagPrice;
    }
}
