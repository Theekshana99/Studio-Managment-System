package ik.ijse.studioclassiceye.tableModel;

public class UserModel {
    private String userName;
    private String empId;
    private String dateOfBirth;
    private String role;

    public UserModel(String userName, String empId, String dateOfBirth, String role) {
        this.userName = userName;
        this.empId = empId;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
