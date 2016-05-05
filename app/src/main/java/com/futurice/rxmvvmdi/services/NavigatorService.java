package com.futurice.rxmvvmdi.services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.futurice.rxmvvmdi.dagger.ActivityScope;

public class NavigatorService {

    private final Context context;

    public NavigatorService(@ActivityScope @NonNull final Context context) {
        this.context = context;
    }

    public void goTo(Class activity) {
        context.startActivity(new Intent(context, activity));
    }
}
