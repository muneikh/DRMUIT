package com.drmuit;

import android.content.Context;

import com.drmuit.manager.HttpManager;
import com.drmuit.service.ApiService;
import com.drmuit.util.Constant;
import com.drmuit.util.MockClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

@Module
public class ApplicationModule {


    private final App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.app;
    }

    @Provides
    @Singleton
    ApiService provideApiService() {
        ApiService service = new RestAdapter.Builder()
                .setEndpoint(Constant.BASE_URL)
                .setClient(new MockClient())
                .build()
                .create(ApiService.class);

        return service;
    }

    @Provides
    @Singleton
    HttpManager provideHttpManager() {
        HttpManager httpManager = new HttpManager(provideApiService());
        return httpManager;
    }
}
