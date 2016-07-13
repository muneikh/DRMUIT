package com.sample;

import android.app.Application;

import timber.log.Timber;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationModule module = new ApplicationModule(this);
        getComponent(DaggerApplicationComponent.builder()
                .applicationModule(module)
                .build());

        getComponent().inject(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private ApplicationComponent applicationComponent;

    public ApplicationComponent getComponent() {
        return this.applicationComponent;
    }

    public void getComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }
}
