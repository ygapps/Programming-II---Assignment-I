package com.youssef;

import javafx.scene.control.SpinnerValueFactory;

public abstract class Sport {

    protected String name = "";
    protected Integer minutesPlayed = 0;
    protected Integer caloriesBurnt = 0;
    protected Float heartRateIncrease = 0.0f;
    protected Integer caloriesBurnRate = 0;
    protected Float heartRateIncreaseRate = 0.0f;


    public Sport(String name, Integer minutesPlayed, Integer caloriesBurnt, Float heartRateIncrease) {
        this.name = name;
        this.minutesPlayed = minutesPlayed;
        this.caloriesBurnt = caloriesBurnt;
        this.heartRateIncrease = heartRateIncrease;
    }

    public String getName() {
        return name;
    }

    public Integer getCaloriesBurnRate() {
        return caloriesBurnRate;
    }

    public void setCaloriesBurnRate(Integer caloriesBurnRate) {
        this.caloriesBurnRate = caloriesBurnRate;
    }

    public Float getHeartRateIncreaseRate() {
        return heartRateIncreaseRate;
    }

    public void setHeartRateIncreaseRate(Float heartRateIncreaseRate) {
        this.heartRateIncreaseRate = heartRateIncreaseRate;
    }

    public Sport(String name, Integer minutesPlayed, Integer caloriesBurnt, Float heartRateIncrease, Integer caloriesBurnRate, Float heartRateIncreaseRate) {
        this.name = name;
        this.minutesPlayed = minutesPlayed;
        this.caloriesBurnt = caloriesBurnt;
        this.heartRateIncrease = heartRateIncrease;
        this.caloriesBurnRate = caloriesBurnRate;
        this.heartRateIncreaseRate = heartRateIncreaseRate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(Integer minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public Integer getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public void setCaloriesBurnt(Integer caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }

    public Float getHeartRateIncrease() {
        return heartRateIncrease;
    }

    public void setHeartRateIncrease(Float heartRateIncrease) {
        this.heartRateIncrease = heartRateIncrease;
    }
}
