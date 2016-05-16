package com.futurice.rxmvvmdi;

import rx.Scheduler;
import rx.schedulers.TestScheduler;

// Possible solution to RxJava caching schedulers
// https://github.com/ReactiveX/RxJava/issues/3914
public class DelegatingTestScheduler extends Scheduler {

    private TestScheduler innerScheduler;

    @Override
    public Worker createWorker() {
        return innerScheduler.createWorker();
    }

    @Override
    public long now() {
        return innerScheduler.now();
    }

    TestScheduler getInnerScheduler() {
        return innerScheduler;
    }

    void setInnerScheduler(TestScheduler innerScheduler) {
        this.innerScheduler = innerScheduler;
    }
}
