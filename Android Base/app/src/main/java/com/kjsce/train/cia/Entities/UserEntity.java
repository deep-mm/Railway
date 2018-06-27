package com.kjsce.train.cia.Entities;

public class UserEntity {
    String name;
    String designation;
    String mobileNumber;

    public UserEntity() {
    }

    public UserEntity(String name, String designation, String mobileNumber) {
        this.name = name;
        this.designation = designation;
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
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}