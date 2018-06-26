package com.kjsce.train.cia.Entities;

public class UserEntity {
    String name;
    String designation;
    String lastLoginTime;
    String mobileNumber;

    public UserEntity() {
    }

    public UserEntity(String name, String designation, String lastLoginTime, String mobileNumber) {
        this.name = name;
        this.designation = designation;
        this.lastLoginTime = lastLoginTime;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}