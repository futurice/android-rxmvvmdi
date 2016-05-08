package com.futurice.rxmvvmdi;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.TestScheduler;

/**
 * Created by osal on 8.5.2016.
 */
public class BaseTest {

    protected TestScheduler mainScheduler;
    protected TestScheduler ioScheduler;
    protected TestScheduler computationScheduler;
    protected TestScheduler newThreadScheduler;

    @Before
    public void init() {
        initSchedulers();
        MockitoAnnotations.initMocks(this);
    }

    private void initSchedulers() {
        mainScheduler = new TestScheduler();
        ioScheduler = new TestScheduler();
        computationScheduler = new TestScheduler();
        newThreadScheduler = new TestScheduler();

        RxJavaPlugins.getInstance().reset();
        RxJavaPlugins.getInstance().registerSchedulersHook(new RxJavaSchedulersHook() {
            @Override
            public Scheduler getComputationScheduler() {
                return computationScheduler;
            }

            @Override
            public Scheduler getIOScheduler() {
                return ioScheduler;
            }

            @Override
            public Scheduler getNewThreadScheduler() {
                return newThreadScheduler;
            }
        });

        RxAndroidPlugins.getInstance().reset();
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return mainScheduler;
            }
        });
    }
}
