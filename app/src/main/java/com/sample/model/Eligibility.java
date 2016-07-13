package com.sample.model;

import com.google.gson.Gson;

public class Eligibility {
    private int code;
    private String description;
    private String result;

    public Eligibility(int code, String description, String result) {
        this.code = code;
        this.description = description;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getResult() {
        return result;
    }

    public static Eligibility fromJson(String json) {
        return new Gson().fromJson(json, Eligibility.class);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
