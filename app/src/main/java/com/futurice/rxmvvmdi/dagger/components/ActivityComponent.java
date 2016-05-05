package com.futurice.rxmvvmdi.dagger.components;

import android.app.Activity;

import com.futurice.rxmvvmdi.activities.MainActivity;
import com.futurice.rxmvvmdi.activities.RxBindingExampleActivity;
import com.futurice.rxmvvmdi.activities.RxPropertyExampleActivity;
import com.futurice.rxmvvmdi.activities.StaticBindingExampleActivity;
import com.futurice.rxmvvmdi.dagger.ActivityScope;
import com.futurice.rxmvvmdi.dagger.modules.ActivityModule;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();

    void inject(MainActivity activity);
    void inject(StaticBindingExampleActivity activity);
    void inject(RxBindingExampleActivity activity);
    void inject(RxPropertyExampleActivity activity);
}
