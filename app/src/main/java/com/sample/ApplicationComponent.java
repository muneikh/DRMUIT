package com.sample;


import com.sample.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    // Field injections of any dependencies
    void inject(App application);
    void inject(MainActivity activity);
}