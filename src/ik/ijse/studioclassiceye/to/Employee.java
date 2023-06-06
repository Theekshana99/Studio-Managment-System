package ik.ijse.studioclassiceye.to;

public class Employee {
    String empId;
    String empName;
    String empAddress;
    String empContact;
    String empSalary;
    String role;

    public Employee(String empId, String empName, String empAddress, String empContact, String empSalary, String role) {
        this.empId = empId;
        this.empName = empName;
        this.empAddress = empAddress;
        this.empContact = empContact;
        this.empSalary = empSalary;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Employee(String empId) {
        this.empId = empId;
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
}
