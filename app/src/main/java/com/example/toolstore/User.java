package com.example.toolstore;

public class User {
    private String userId;
    private String userName;
    private String userCity;
    private int userZIPCode;
    private int userContact;

    public User(){
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String id) {
        this.userId = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public int getUserZIPCode() {
        return userZIPCode;
    }

    public void setUserZIPCode(int ZIPCode) {
        this.userZIPCode = ZIPCode;
    }

    public int getUserContact() {
        return userContact;
    }

    public void setUserContact(int userContact) {
        this.userContact = userContact;
    }
}
