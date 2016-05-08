package com.futurice.rxmvvmdi.viewmodels;

import com.futurice.rxmvvmdi.BaseTest;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.observers.TestSubscriber;

public class RxBindingExampleViewModelTest extends BaseTest {

    private RxBindingExampleViewModel viewModel;

    @Before
    public void setup() {
        viewModel = new RxBindingExampleViewModel();
    }

    @Test
    public void timeStream_incrementsEveryTenMiliseconds() {
        TestSubscriber subscriber = new TestSubscriber();
        viewModel.getTimeStream().subscribe(subscriber);
        computationScheduler.advanceTimeBy(20, TimeUnit.MILLISECONDS);
        subscriber.assertValues("00:00:00", "00:00:01");
    }
}
