package com.futurice.rxmvvmdi.dagger.components;

import com.futurice.rxmvvmdi.RxMvvmApp;
import com.futurice.rxmvvmdi.dagger.modules.ActivityModule;
import com.futurice.rxmvvmdi.dagger.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {

    void inject(RxMvvmApp app);

    ActivityComponent plusActivity(ActivityModule activityModule);

    class Initializer {
        public static AppComponent init(final RxMvvmApp app) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .build();
        }
    }
}
