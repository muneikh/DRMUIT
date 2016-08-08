package com.drmuit.manager;

import com.drmuit.model.Eligibility;
import com.drmuit.model.ResponseModel;
import com.drmuit.model.Reward;
import com.drmuit.service.ApiService;

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