package com.kjsce.train.cia.Activity;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kjsce.train.cia.Entities.CardEntity;
import com.kjsce.train.cia.Entities.TrainEntity;
import com.kjsce.train.cia.Entities.UserEntity;
import com.kjsce.train.cia.Entities.UserNotificationEntity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedData {

    public SharedPreferences pref;
    public SharedPreferences.Editor editor;
    public Gson gson;
    public Context context;

    public SharedData(Context c) {
        this.context = c;
        pref = c.getSharedPreferences("MyPref", 0);
        editor = pref.edit();
        gson = new Gson();
    }

    public Boolean isLoggedIn(){
        Boolean check = pref.getBoolean("login", false);
        return check;
    }

    public void isUserVerified(Boolean check){
        editor.putBoolean("user_verified", check).commit();
    }

    public Boolean isUserVerified(){
        Boolean check = pref.getBoolean("user_verified", false);
        return check;
    }

    public void isLoggedIn(Boolean check){
        editor.putBoolean("login", check).commit();
    }

    public void setPlaceOfInspection(String place){
        editor.putString("place", place).commit();
    }

    public String getPlaceOfInspection(){
        String place = pref.getString("place", "");
        return place;
    }

    public void setTrain(String train){
        editor.putString("train", train).commit();
    }

    public String getTrain(){
        String train = pref.getString("train", "");
        return train;
    }

    public void setType(String type){
        editor.putString("type", type).commit();
    }

    public String getType(){
        String type = pref.getString("type", "");
        return type;
    }

    public void setBogie(String bogie){
        editor.putString("bogie", bogie).commit();
    }

    public String getBogie(){
        String bogie = pref.getString("bogie", "");
        return bogie;
    }

    public void setTrainList(List<String> train_list){
        String train = gson.toJson(train_list);
        editor.putString("train_list",train).commit();
    }

    public List<String> getUserList(){
        String json = pref.getString("user_list", "");
        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> user_list = gson.fromJson(json, listType);
        return user_list;
    }

    public void setUserList(List<String> user_list){
        String user = gson.toJson(user_list);
        editor.putString("user_list",user).commit();
    }

    public List<String> getTrainList(){
        String json = pref.getString("train_list", "");
        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> train_list = gson.fromJson(json, listType);
        return train_list;
    }

    public void setTypeList(List<Boolean> type_list){
        String type = gson.toJson(type_list);
        editor.putString("type_list",type).commit();
    }

    public List<Boolean> getTypeList(){
        String json = pref.getString("type_list", "");
        Type listType = new TypeToken<List<Boolean>>() {}.getType();
        List<Boolean> type_list = gson.fromJson(json, listType);
        return type_list;
    }

    public void setUserEntityList(List<UserEntity> userEntityList){
        String type = gson.toJson(userEntityList);
        editor.putString("userEntityList",type).commit();
    }

    public List<UserEntity> getUserEntityList(){
        String json = pref.getString("userEntityList", "");
        Type listType = new TypeToken<List<UserEntity>>() {}.getType();
        List<UserEntity> type_list = gson.fromJson(json, listType);
        return type_list;
    }

    public void setNotificationEntityList(List<UserNotificationEntity> userEntityNotification){
        String type = gson.toJson(userEntityNotification);
        editor.putString("userEntityNotification",type).commit();
    }

    public List<UserNotificationEntity> getNotificationEntityList(){
        String json = pref.getString("userEntityNotification", "");
        Type listType = new TypeToken<List<UserNotificationEntity>>() {}.getType();
        List<UserNotificationEntity> type_list = gson.fromJson(json, listType);
        return type_list;
    }

    public void setUserEntity(UserEntity userEntity){
        String json = gson.toJson(userEntity);
        editor.putString("userEntity",json).commit();
    }

    public UserEntity getUserEntity(){
        String json = pref.getString("userEntity", "");
        UserEntity userEntity = gson.fromJson(json, UserEntity.class);
        return userEntity;
    }

    public void setCardEntity(CardEntity cardEntity){
        String json = gson.toJson(cardEntity);
        editor.putString("cardEntity",json).commit();
    }

    public CardEntity getCardEntity(){
        String json = pref.getString("cardEntity", "");
        CardEntity cardEntity = gson.fromJson(json, CardEntity.class);
        return cardEntity;
    }

    public void setTrainEntity(TrainEntity trainEntity){
        String json = gson.toJson(trainEntity);
        editor.putString("trainEntity",json).commit();
    }

    public TrainEntity getTrainEntity(){
        String json = pref.getString("trainEntity", "");
        TrainEntity trainEntity = gson.fromJson(json, TrainEntity.class);
        return trainEntity;
    }

    public void clearAll(){
        editor.clear();
        editor.commit();
    }

    public void setCheckedList(List<Boolean> checked_list){
        String type = gson.toJson(checked_list);
        editor.putString("checked_list",type).commit();
    }

    public List<Boolean> getCheckedList(){
        String json = pref.getString("checked_list", "");
        Type listType = new TypeToken<List<Boolean>>() {}.getType();
        List<Boolean> checked_list = gson.fromJson(json, listType);
        return checked_list;
    }

    public void setFirstTime(List<Boolean> firstTime){
        String type = gson.toJson(firstTime);
        editor.putString("firstTime",type).commit();
    }

    public List<Boolean> getFirstTime(){
        String json = pref.getString("firstTime", "");
        Type listType = new TypeToken<List<Boolean>>() {}.getType();
        List<Boolean> firstTime = gson.fromJson(json, listType);
        return firstTime;
    }

    public void setCardEntityToUpload(List<CardEntity> cardEntity){
        String type = gson.toJson(cardEntity);
        editor.putString("cardEntity_upload",type).commit();
    }

    public List<CardEntity> getCardEntityToUpload(){
        String json = pref.getString("cardEntity_upload", "");
        Type listType = new TypeToken<List<CardEntity>>() {}.getType();
        List<CardEntity> cardEntities = gson.fromJson(json, listType);
        return cardEntities;
    }

    public void setTypeCheckedList(List<Boolean> checked_list){
        String type = gson.toJson(checked_list);
        editor.putString("checked_list_type",type).commit();
    }

    public List<Boolean> getTypeCheckedList(){
        String json = pref.getString("checked_list_type", "");
        Type listType = new TypeToken<List<Boolean>>() {}.getType();
        List<Boolean> checked_list = gson.fromJson(json, listType);
        return checked_list;
    }

    public void setStatusCheckedList(List<Boolean> checked_list){
        String type = gson.toJson(checked_list);
        editor.putString("checked_list_status",type).commit();
    }

    public List<Boolean> getStatusCheckedList(){
        String json = pref.getString("checked_list_status", "");
        Type listType = new TypeToken<List<Boolean>>() {}.getType();
        List<Boolean> checked_list = gson.fromJson(json, listType);
        return checked_list;
    }

    public List<String> getDateList(){
        String json = pref.getString("date_list", "");
        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> date_list = gson.fromJson(json, listType);
        return date_list;
    }

    public void setDateList(List<String> date_list){
        String date = gson.toJson(date_list);
        editor.putString("date_list",date).commit();
    }


}