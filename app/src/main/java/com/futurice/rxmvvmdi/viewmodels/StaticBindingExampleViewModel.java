package com.futurice.rxmvvmdi.viewmodels;

import android.support.annotation.NonNull;

import rx.subscriptions.CompositeSubscription;

public class StaticBindingExampleViewModel extends ViewModel {

    public enum CoffeeType {
        CAPRICCIO,
        LIVANTO
    }

    public Capsule capsule = new Capsule(CoffeeType.LIVANTO, "Livanto");

    @Override
    protected void subscribe(@NonNull CompositeSubscription subscriptions) {
    }

    public class Capsule {
        public final CoffeeType type;
        public final String name;

        public Capsule(final CoffeeType type, @NonNull final String name) {
            this.type = type;
            this.name = name;
        }
    }
}
