package com.kjsce.train.cia.Entities;

public class UserEntity {
    String name;
    String designation;
    String lastLoginTime;

    public UserEntity() {
    }

    public UserEntity(String name, String designation, String lastLoginTime) {
        this.name = name;
        this.designation = designation;
        this.lastLoginTime = lastLoginTime;
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

    @Override
    public String toString() {
        return "UserEntity{" +
                "name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                '}';
    }
}
