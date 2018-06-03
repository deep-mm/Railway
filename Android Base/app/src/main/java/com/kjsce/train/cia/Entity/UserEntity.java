package com.kjsce.train.cia.Entity;

public class UserEntity
{
    String userId;
    String name;
    String emailId;
    String password;
    String lastLoginTime;
    String type;
    String designation;

    public UserEntity() {
    }

    public UserEntity(String userId, String name, String emailId, String password, String lastLoginTime, String type, String designation) {
        this.userId = userId;
        this.name = name;
        this.emailId = emailId;
        this.password = password;
        this.lastLoginTime = lastLoginTime;
        this.type = type;
        this.designation = designation;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", type='" + type + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }
}
