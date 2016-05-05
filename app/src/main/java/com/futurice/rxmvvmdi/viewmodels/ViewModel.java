package com.futurice.rxmvvmdi.viewmodels;

import android.support.annotation.NonNull;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by osal on 4.5.2016.
 */
public abstract class ViewModel {

    private final CompositeSubscription subscriptions;

    public ViewModel() {
        subscriptions = new CompositeSubscription();
    }

    public void bind() {
        subscriptions.clear();
        CompositeSubscription newSubscriptions = new CompositeSubscription();
        subscribe(newSubscriptions);
        subscriptions.add(newSubscriptions);
    }

    public void unbind() {
        subscriptions.clear();
    }

    protected abstract void subscribe(@NonNull final CompositeSubscription subscriptions);
}
