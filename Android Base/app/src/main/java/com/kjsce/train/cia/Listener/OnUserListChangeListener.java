package com.kjsce.train.cia.Listener;

import com.kjsce.train.cia.Entities.UserEntity;

import java.util.List;

public interface OnUserListChangeListener {
    void OnDataChanged(List<UserEntity> newUserList);
}
