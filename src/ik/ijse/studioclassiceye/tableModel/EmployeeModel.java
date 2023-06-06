package ik.ijse.studioclassiceye.tableModel;

public class EmployeeModel {
    private String empId;
    private String empName;
    private String empAddress;
    private String empContact;
    private String empSalary;
    private String empRole;

    public EmployeeModel(String empId, String empName, String empAddress, String empContact, String empSalary, String empRole) {
        this.empId = empId;
        this.empName = empName;
        this.empAddress = empAddress;
        this.empContact = empContact;
        this.empSalary = empSalary;
        this.empRole = empRole;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpContact() {
        return empContact;
    }

    public void setEmpContact(String empContact) {
        this.empContact = empContact;
    }

    public String getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(String empSalary) {
        this.empSalary = empSalary;
    }

    public String getEmpRole() {
        return empRole;
    }

    public void setEmpRole(String empRole) {
        this.empRole = empRole;
    }
}
