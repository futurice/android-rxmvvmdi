package com.futurice.rxmvvmdi;

import org.junit.Before;
import org.junit.BeforeClass;
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
public abstract class RxTest {

    private static final DelegatingTestScheduler delegatingMainScheduler = new DelegatingTestScheduler();
    private static final DelegatingTestScheduler delegatingIoScheduler = new DelegatingTestScheduler();
    private static final DelegatingTestScheduler delegatingComputationScheduler = new DelegatingTestScheduler();
    private static final DelegatingTestScheduler delegatingNewThreadScheduler = new DelegatingTestScheduler();

    @BeforeClass
    public static void initSchedulers() {
        RxJavaPlugins.getInstance().reset();
        RxJavaPlugins.getInstance().registerSchedulersHook(new RxJavaSchedulersHook() {
            @Override
            public Scheduler getComputationScheduler() {
                return delegatingComputationScheduler;
            }

            @Override
            public Scheduler getIOScheduler() {
                return delegatingIoScheduler;
            }

            @Override
            public Scheduler getNewThreadScheduler() {
                return delegatingNewThreadScheduler;
            }
        });

        RxAndroidPlugins.getInstance().reset();
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return delegatingMainScheduler;
            }
        });
    }

    @Before
    public void init() {
        resetSchedulers();
        MockitoAnnotations.initMocks(this);
        setup();
    }

    private void resetSchedulers() {
        delegatingMainScheduler.setInnerScheduler(new TestScheduler());
        delegatingIoScheduler.setInnerScheduler(new TestScheduler());
        delegatingComputationScheduler.setInnerScheduler(new TestScheduler());
        delegatingNewThreadScheduler.setInnerScheduler(new TestScheduler());
    }

    protected abstract void setup();

    protected TestScheduler mainScheduler() {
        return delegatingMainScheduler.getInnerScheduler();
    }

    protected TestScheduler ioScheduler() {
        return delegatingIoScheduler.getInnerScheduler();
    }

    protected TestScheduler computationScheduler() {
        return delegatingComputationScheduler.getInnerScheduler();
    }

    protected TestScheduler newThreadScheduler() {
        return delegatingNewThreadScheduler.getInnerScheduler();
    }
}
