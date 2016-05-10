package com.futurice.rxmvvmdi.dagger.modules;

import android.app.Activity;
import android.content.Context;

import com.futurice.rxmvvmdi.dagger.ActivityScope;
import com.futurice.rxmvvmdi.services.NavigatorService;
import com.futurice.rxmvvmdi.services.SystemMonitorService;
import com.futurice.rxmvvmdi.viewmodels.MainViewModel;
import com.futurice.rxmvvmdi.viewmodels.RxBindingExampleViewModel;
import com.futurice.rxmvvmdi.viewmodels.RxPropertyExampleViewModel;
import com.futurice.rxmvvmdi.viewmodels.StaticBindingExampleViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity activity() {
        return this.activity;
    }

    @Provides
    @ActivityScope
    Context context() { return this.activity; }

    @Provides
    @ActivityScope
    NavigatorService provideNavigatorService(@ActivityScope Context context) {
        return new NavigatorService(context);
    }
}
