package com.futurice.rxmvvmdi.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.test.suitebuilder.annotation.SmallTest;

import com.futurice.rxmvvmdi.BaseTest;
import com.futurice.rxmvvmdi.activities.RxBindingExampleActivity;
import com.futurice.rxmvvmdi.activities.StaticBindingExampleActivity;
import com.futurice.rxmvvmdi.services.NavigatorService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

@SmallTest
public class MainActivityViewModelTest extends BaseTest {

    @Mock
    private NavigatorService navigator;

    @Mock
    private Context context;

    private MainViewModel viewModel;

    protected void setup() {
        viewModel = new MainViewModel(context, navigator);
    }

    @Test
    public void selectStaticDataBinding_navigatesToStaticBindingExample() {
        viewModel.selectStaticDataBinding();
        verify(context).startActivity(new Intent(context, StaticBindingExampleActivity.class));
    }

    @Test
    public void selectRxBinding_navigatesToRxBindingExample() {
        viewModel.selectStaticDataBinding();
        verify(navigator).goTo(RxBindingExampleActivity.class);
    }
}
