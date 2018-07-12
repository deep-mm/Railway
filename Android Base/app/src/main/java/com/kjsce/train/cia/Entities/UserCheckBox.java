package com.kjsce.train.cia.Entities;

public class UserCheckBox {
    UserEntity userEntity;
    Boolean checked;

    public UserCheckBox(UserEntity userEntity, Boolean checked) {
        this.userEntity = userEntity;
        this.checked = checked;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
