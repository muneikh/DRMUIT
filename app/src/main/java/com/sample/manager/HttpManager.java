package com.sample.manager;

import com.sample.model.Eligibility;
import com.sample.model.ResponseModel;
import com.sample.model.Reward;
import com.sample.service.ApiService;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HttpManager {

    public ApiService mApiService;

    public HttpManager(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    public Observable getRewards(int accountNumber, int channelId) {
        return Observable.zip(
                mApiService.checkEligibility(accountNumber),
                mApiService.getRewards(channelId),
                (eligibility, reward) -> composeResults(eligibility, reward))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private ResponseModel composeResults(Eligibility eligibility, Reward reward) {
        return new ResponseModel(eligibility, reward);
    }
}