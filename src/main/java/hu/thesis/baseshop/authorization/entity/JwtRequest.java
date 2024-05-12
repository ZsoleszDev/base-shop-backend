package hu.thesis.baseshop.authorization.entity;

public class JwtRequest {


    private String userName;
    private String userPassword;

    private String userRole;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String userRole() {
        return userRole;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
