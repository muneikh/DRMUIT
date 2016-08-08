package com.drmuit.service;

import com.drmuit.model.Eligibility;
import com.drmuit.model.Reward;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface ApiService {

    @GET("/eligibility")
    Observable<Eligibility> checkEligibility(@Query("account") long accountNumber);

    @GET("/reward")
    Observable<Reward> getRewards(@Query("id") int channelId);
}