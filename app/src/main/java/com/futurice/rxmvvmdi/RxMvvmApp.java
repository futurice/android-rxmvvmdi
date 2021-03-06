package com.futurice.rxmvvmdi;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.futurice.rxmvvmdi.dagger.components.AppComponent;

/**
 * Created by osal on 3.5.2016.
 */
public class RxMvvmApp extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        appComponent = AppComponent.Initializer.init(this);
        appComponent.inject(this);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
