package com.futurice.rxmvvmdi.viewmodels;

import android.support.annotation.NonNull;

import rx.subscriptions.CompositeSubscription;

/**
 * Base VM class that can be used by activities and fragments to manage VM lifecycles in a
 * consistent manner.
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
