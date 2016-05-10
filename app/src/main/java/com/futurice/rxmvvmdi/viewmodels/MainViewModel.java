package com.futurice.rxmvvmdi.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.futurice.rxmvvmdi.activities.RxBindingExampleActivity;
import com.futurice.rxmvvmdi.activities.RxPropertyExampleActivity;
import com.futurice.rxmvvmdi.activities.StaticBindingExampleActivity;
import com.futurice.rxmvvmdi.dagger.ActivityScope;
import com.futurice.rxmvvmdi.services.NavigatorService;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public class MainViewModel extends ViewModel {

    private final NavigatorService navigatorService;
    private final Context context;

    @Inject
    public MainViewModel(
            @NonNull final Context context,
            @NonNull final NavigatorService navigatorService) {
        this.context = context;
        this.navigatorService = navigatorService;
    }

    @Override
    protected void subscribe(@NonNull CompositeSubscription subscriptions) {
    }

    public void selectStaticDataBinding() {
        context.startActivity(new Intent(context, StaticBindingExampleActivity.class));
    }

    public void selectRxBinding() {
        navigatorService.goTo(RxBindingExampleActivity.class);
    }

    public void selectRxProperty() {
        navigatorService.goTo(RxPropertyExampleActivity.class);
    }
}
