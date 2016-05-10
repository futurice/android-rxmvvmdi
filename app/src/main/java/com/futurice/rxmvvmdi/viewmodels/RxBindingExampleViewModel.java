package com.futurice.rxmvvmdi.viewmodels;

import android.support.annotation.NonNull;

import org.threeten.bp.Duration;
import org.threeten.bp.LocalTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

public class RxBindingExampleViewModel extends ViewModel {

    private final Observable<String> timeStream;
    private final Observable<Integer> highLoadStream;
    private final PublishSubject<Integer> calculateSubject;

    @Inject
    public RxBindingExampleViewModel() {
        final long intervalMs = 10;
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss:SS");

        timeStream = Observable
                .interval(intervalMs, TimeUnit.MILLISECONDS)
                .onBackpressureDrop()
                .map(beats -> Duration.ofMillis(intervalMs * beats))
                .map(duration -> formatter.format(LocalTime.MIDNIGHT.plus(duration)));

        calculateSubject = PublishSubject.create();
        highLoadStream = calculateSubject
                .scan((sum, value) -> ++sum)
                .map(iteration -> {
                    // Simulate high processing load
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {}
                    return iteration;
                });
    }

    @Override
    protected void subscribe(@NonNull CompositeSubscription subscriptions) {
    }

    public void calculate() {
        calculateSubject.onNext(1);
    }

    public Observable<String> getTimeStream() {
        return timeStream;
    }

    public Observable<Integer> getHighLoadStream() {
        return highLoadStream;
    }
}
