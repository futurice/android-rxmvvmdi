package com.futurice.rxmvvmdi;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.TestScheduler;

/**
 * Base class for tests that initializes all mocks annotated with @Mock using Mockito.
 * It also sets up TestSchedulers for all Rx schedulers so Rx streams can be tested more reliably
 * regardless of which thread they run on normally.
 */
public abstract class BaseTest {

    protected TestScheduler mainScheduler;
    protected TestScheduler ioScheduler;
    protected TestScheduler computationScheduler;
    protected TestScheduler newThreadScheduler;

    protected abstract void setup();

    @Before
    public void init() {
        initSchedulers();
        MockitoAnnotations.initMocks(this);
        setup();
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
