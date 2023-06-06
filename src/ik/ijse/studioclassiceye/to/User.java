package ik.ijse.studioclassiceye.to;

public class User {
    private String UserName;
    private String EmpId;
    private String Password;
    private String DateOfBirth;
    private String Role;

    public User(String userName) {
        UserName = userName;
    }

    public User(String userName, String empId, String password, String dateOfBirth, String role) {
        UserName = userName;
        EmpId = empId;
        Password = password;
        DateOfBirth = dateOfBirth;
        Role = role;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
