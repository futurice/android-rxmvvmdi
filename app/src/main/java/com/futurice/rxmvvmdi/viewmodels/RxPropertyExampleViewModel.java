package com.futurice.rxmvvmdi.viewmodels;

import android.support.annotation.NonNull;

import com.futurice.rxmvvmdi.services.SystemMonitorService;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

public class RxPropertyExampleViewModel extends ViewModel {

    public final RxProperty<String> cpuUsage;
    public final RxProperty<String> maxCpuUsage;

    private final SystemMonitorService systemMonitorService;

    public RxPropertyExampleViewModel(@NonNull final SystemMonitorService systemMonitorService) {
        this.systemMonitorService = systemMonitorService;
        cpuUsage = new RxProperty<>();
        maxCpuUsage = new RxProperty<>();
    }

    @Override
    protected void subscribe(@NonNull CompositeSubscription subscriptions) {
        Observable<Float> cpuUtzStream = systemMonitorService
                .getCpuUtilizationStream()
                .share();
        subscriptions.add(cpuUtzStream
                .map(usage -> usage + "%")
                .subscribe(cpuUsage)
        );
        subscriptions.add(cpuUtzStream
                .scan((max, value) -> Math.max(max, value))
                .map(usage -> usage + "%")
                .subscribe(maxCpuUsage)
        );
    }
}
