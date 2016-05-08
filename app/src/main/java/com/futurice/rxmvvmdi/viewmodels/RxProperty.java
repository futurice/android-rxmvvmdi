package com.futurice.rxmvvmdi.viewmodels;

import android.databinding.Observable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

/**
 * Created by osal on 26.4.2016.
 */
public class RxProperty<T> extends ObservableField<T> implements rx.Observer<T>, Observable {

    private final SerializedSubject<T, T> subject = new SerializedSubject(PublishSubject.create());
    private final rx.Observable<T> output;
    private T value;

    public RxProperty() {
        output = subject.asObservable();
        init();
    }

    public RxProperty(final T startValue) {
        value = startValue;
        output = subject.startWith(startValue);
        init();
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T t) {
        onNext(t);
    }

    @NonNull
    public rx.Observable<T> getStream() {
        return output;
    }

    @Override
    public void onCompleted() {
        subject.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        subject.onError(e);
    }

    @Override
    public void onNext(T t) {
        subject.onNext(t);
    }

    private void init() {
        output.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<T>() {
                    @Override
                    public void call(T t) {
                        value = t;
                        notifyChange();
                    }
                });
    }
}
