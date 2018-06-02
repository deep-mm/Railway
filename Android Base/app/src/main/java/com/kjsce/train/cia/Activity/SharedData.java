package com.kjsce.train.cia.Activity;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kjsce.train.cia.Entity.Card.DetailedCard;
import com.kjsce.train.cia.Entity.UserEntity;

import java.lang.reflect.Type;
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

    public void setUserEntity(UserEntity userEntity){
        String card = gson.toJson(userEntity);
        editor.putString("userEntity",card).commit();
    }

    public UserEntity getUserEntity(){
        String json = pref.getString("userEntity", "");
        Type listType = new TypeToken<UserEntity>() {}.getType();
        UserEntity userEntity = gson.fromJson(json, listType);
        return userEntity;
    }

    public void clearAll(){
        editor.clear();
        editor.commit();
    }


}