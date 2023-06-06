package ik.ijse.studioclassiceye.to;

public class Package {
    private String packageId;
    private String packageDescription;
    private String packagePrice;

    public Package(String packageId) {
        this.packageId = packageId;
    }

    public Package(String packageId, String packageDescription, String packagePrice) {
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

    public void setPackagePrice(String packagePrice) {
        this.packagePrice = packagePrice;
    }
}
