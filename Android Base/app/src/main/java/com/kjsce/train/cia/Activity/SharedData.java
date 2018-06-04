package com.kjsce.train.cia.Activity;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kjsce.train.cia.Entity.BogeyEntity;
import com.kjsce.train.cia.Entity.Card.DetailedCard;
import com.kjsce.train.cia.Entity.TrainEntity;
import com.kjsce.train.cia.Entity.UserEntity;

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

    public void isLoggedIn(Boolean check){
        editor.putBoolean("login", check).commit();
    }

    public void setType(String type){
        editor.putString("type", type).commit();
    }

    public String getType(){
        String type = pref.getString("type", "");
        return type;
    }

    public void setStation(String type){
        editor.putString("station", type).commit();
    }

    public String getStation(){
        String type = pref.getString("station", "");
        return type;
    }

    public void setTrain(String train){
        editor.putString("train", train).commit();
    }

    public String getTrain(){
        String train = pref.getString("train", "");
        return train;
    }

    public void setTrainNo(String train){
        editor.putString("train_no", train).commit();
    }

    public String getTrainNo(){
        String train = pref.getString("train_no", "");
        return train;
    }

    public void setBogie(String bogie){
        editor.putString("bogie", bogie).commit();
    }

    public String getBogie(){
        String bogie = pref.getString("bogie", "");
        return bogie;
    }

    public void setCoachList(List<String> coach_list){
        String coach = gson.toJson(coach_list);
        editor.putString("coach_list",coach).commit();
    }

    public List<String> getCoachList(){
        String json = pref.getString("coach_list", "");
        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> coach_list = gson.fromJson(json, listType);
        return coach_list;
    }

    public void setTrainList(List<String> train_list){
        String train = gson.toJson(train_list);
        editor.putString("train_list",train).commit();
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

    public void setDetailedCards(List<DetailedCard> cards){
        String card = gson.toJson(cards);
        editor.putString("card_list",card).commit();
    }

    public List<DetailedCard> getDetailedCard(){
        String json = pref.getString("card_list", "");
        Type listType = new TypeToken<List<DetailedCard>>() {}.getType();
        List<DetailedCard> card_list = gson.fromJson(json, listType);
        return card_list;
    }

    public void setBogieEntity(List<BogeyEntity> cards){
        String card = gson.toJson(cards);
        editor.putString("bogey_entity",card).commit();
    }

    public List<BogeyEntity> getBogieEntity(){
        String json = pref.getString("bogey_entity", "");
        Type listType = new TypeToken<List<BogeyEntity>>() {}.getType();
        List<BogeyEntity> card_list = gson.fromJson(json, listType);
        return card_list;
    }

    public void setTrainEntityList(List<TrainEntity> cards){
        String card = gson.toJson(cards);
        editor.putString("train_entity_list",card).commit();
    }

    public List<TrainEntity> getTrainEntityList(){
        String json = pref.getString("train_entity_list", "");
        Type listType = new TypeToken<List<TrainEntity>>() {}.getType();
        List<TrainEntity> card_list = gson.fromJson(json, listType);
        return card_list;
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

    public void clear(){
        List<BogeyEntity> bogeyEntities = new ArrayList<BogeyEntity>();
        setBogieEntity(bogeyEntities);
    }


}