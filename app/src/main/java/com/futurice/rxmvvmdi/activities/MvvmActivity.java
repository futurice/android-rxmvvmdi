package com.futurice.rxmvvmdi.activities;

import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;

import com.futurice.rxmvvmdi.viewmodels.ViewModel;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by osal on 4.5.2016.
 */
public abstract class MvvmActivity extends AppCompatActivity {

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    @CallSuper
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewModel().bind();
    }

    @CallSuper
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewModel().unbind();
    }

    @CallSuper
    @Override
    public void onResume() {
        // Bind view to viewmodel during resume
        super.onResume();
        subscriptions.clear();
        CompositeSubscription newSubscriptions = new CompositeSubscription();
        bindView(newSubscriptions);
        subscriptions.add(newSubscriptions);
    }

    @CallSuper
    @Override
    public void onPause() {
        // Unbind view from viewmodel during pause (avoid unnecessary view updates)
        super.onPause();
        subscriptions.clear();
    }

    protected abstract ViewModel getViewModel();

    protected void bindView(CompositeSubscription subscriptions) {}
}
