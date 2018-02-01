package com.example.administrator.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/7 0007.
 */

public class CityInfo {
    private String airCondition;
    private String city;
    private String coldIndex;
    private Date date;
    private String distrct;
    private String dressingIndex;
    private String exerciseIndex;
    private List<FutureInfo> future;//数组
    private String humidity;
    private String pollutionIndex;
    private String province;
    private String sunrise;
    private String sunset;
    private String temperature;
    private String time;
    private String updateTime;
    private String washIndex;
    private String weather;
    private String week;
    private String wind;


    public CityInfo() {
    }

    public CityInfo(String airCondition, String city, String coldIndex, Date date, String distrct, String dressingIndex, String exerciseIndex, List<FutureInfo> future, String humidity, String pollutionIndex, String province, String sunrise, String sunset, String temperature, String time, String updateTime, String washIndex, String weather, String week, String wind) {
        this.airCondition = airCondition;
        this.city = city;
        this.coldIndex = coldIndex;
        this.date = date;
        this.distrct = distrct;
        this.dressingIndex = dressingIndex;
        this.exerciseIndex = exerciseIndex;
        this.future = future;
        this.humidity = humidity;
        this.pollutionIndex = pollutionIndex;
        this.province = province;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.temperature = temperature;
        this.time = time;
        this.updateTime = updateTime;
        this.washIndex = washIndex;
        this.weather = weather;
        this.week = week;
        this.wind = wind;
    }

    public String getAirCondition() {
        return airCondition;
    }

    public void setAirCondition(String airCondition) {
        this.airCondition = airCondition;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getColdIndex() {
        return coldIndex;
    }

    public void setColdIndex(String coldIndex) {
        this.coldIndex = coldIndex;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDistrct() {
        return distrct;
    }

    public void setDistrct(String distrct) {
        this.distrct = distrct;
    }

    public String getDressingIndex() {
        return dressingIndex;
    }

    public void setDressingIndex(String dressingIndex) {
        this.dressingIndex = dressingIndex;
    }

    public String getExerciseIndex() {
        return exerciseIndex;
    }

    public void setExerciseIndex(String exerciseIndex) {
        this.exerciseIndex = exerciseIndex;
    }

    public List<FutureInfo> getFuture() {
        return future;
    }

    public void setFuture(List<FutureInfo> future) {
        this.future = future;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPollutionIndex() {
        return pollutionIndex;
    }

    public void setPollutionIndex(String pollutionIndex) {
        this.pollutionIndex = pollutionIndex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getWashIndex() {
        return washIndex;
    }

    public void setWashIndex(String washIndex) {
        this.washIndex = washIndex;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}
