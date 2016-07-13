package com.sample.model;

public class ResponseModel {
    private Eligibility eligibility;
    private Reward reward;

    public ResponseModel(Eligibility eligibility, Reward reward) {
        this.eligibility = eligibility;
        this.reward = reward;
    }

    public Eligibility getEligibility() {
        return eligibility;
    }

    public Reward getReward() {
        return reward;
    }
}
