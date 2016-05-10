package com.futurice.rxmvvmdi.dagger.modules;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.futurice.rxmvvmdi.RxMvvmApp;
import com.futurice.rxmvvmdi.services.IBackendService;
import com.futurice.rxmvvmdi.services.SystemMonitorService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

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
