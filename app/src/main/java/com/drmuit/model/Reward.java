package com.drmuit.model;

import com.google.gson.Gson;

public class Reward {
    private String description;

    public Reward(String reward) {
        this.description = reward;
    }

    public String getDescription() {
        return description;
    }

    public static Reward fromJson(String json) {
        return new Gson().fromJson(json, Reward.class);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
