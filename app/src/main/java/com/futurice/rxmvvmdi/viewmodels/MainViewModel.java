package com.futurice.rxmvvmdi.viewmodels;

import android.support.annotation.NonNull;

import com.futurice.rxmvvmdi.activities.RxBindingExampleActivity;
import com.futurice.rxmvvmdi.activities.RxPropertyExampleActivity;
import com.futurice.rxmvvmdi.activities.StaticBindingExampleActivity;
import com.futurice.rxmvvmdi.services.NavigatorService;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public class MainViewModel extends ViewModel {

    private final NavigatorService navigatorService;

    @Inject
    public MainViewModel(@NonNull final NavigatorService navigatorService) {
        this.navigatorService = navigatorService;
    }

    @Override
    protected void subscribe(@NonNull CompositeSubscription subscriptions) {
    }

    public void selectStaticDataBinding() {
        navigatorService.goTo(StaticBindingExampleActivity.class);
    }

    public void selectRxBinding() {
        navigatorService.goTo(RxBindingExampleActivity.class);
    }

    public void selectRxProperty() {
        navigatorService.goTo(RxPropertyExampleActivity.class);
    }
}
