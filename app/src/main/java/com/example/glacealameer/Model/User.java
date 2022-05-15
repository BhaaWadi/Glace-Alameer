package com.example.glacealameer.Model;

import java.io.Serializable;

public class User  implements Serializable {

    private String fullName;
    private String email;
    private String mobile;
    private String image;
    private String userID;
     private  double total;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    private  boolean isAdmin;

    public User() {
    }

    public User(String fullName, String email, String mobile, String image, String userID, boolean isAdmin) {
        this.fullName = fullName;
        this.email = email;
        this.mobile = mobile;
        this.image = image;
        this.userID = userID;
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getImage() {
        return image;
    }

    public String getUserID() {
        return userID;
    }
}
