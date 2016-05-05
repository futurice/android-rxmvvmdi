package com.futurice.rxmvvmdi.dagger.modules;

import com.futurice.rxmvvmdi.RxMvvmApp;
import com.futurice.rxmvvmdi.services.SystemMonitorService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final RxMvvmApp app;

    public AppModule(final RxMvvmApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public RxMvvmApp provideApp() {
        return app;
    }

    @Provides
    @Singleton
    public SystemMonitorService provideSystemMonitorService() {
        return new SystemMonitorService();
    }
}
