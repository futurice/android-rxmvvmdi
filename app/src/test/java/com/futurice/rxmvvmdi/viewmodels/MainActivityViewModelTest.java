package com.futurice.rxmvvmdi.viewmodels;

import android.test.suitebuilder.annotation.SmallTest;

import com.futurice.rxmvvmdi.RxTest;
import com.futurice.rxmvvmdi.activities.StaticBindingExampleActivity;
import com.futurice.rxmvvmdi.services.NavigatorService;

import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

@SmallTest
public class MainActivityViewModelTest extends RxTest {

    @Mock
    private NavigatorService navigator;

    private MainViewModel viewModel;

    protected void setup() {
        viewModel = new MainViewModel(navigator);
    }

    @Test
    public void selectStaticDataBinding_navigatesToStaticBindingExample() {
        viewModel.selectStaticDataBinding();
        verify(navigator).goTo(StaticBindingExampleActivity.class);
    }
}
