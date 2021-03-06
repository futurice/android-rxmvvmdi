package com.futurice.rxmvvmdi.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.futurice.rxmvvmdi.R;
import com.futurice.rxmvvmdi.RxMvvmApp;
import com.futurice.rxmvvmdi.dagger.modules.ActivityModule;
import com.futurice.rxmvvmdi.databinding.ActivityRxPropertyExampleBinding;
import com.futurice.rxmvvmdi.viewmodels.RxPropertyExampleViewModel;
import com.futurice.rxmvvmdi.viewmodels.ViewModel;

import javax.inject.Inject;

/**
 * This activity is for demonstrating the use of RxProperties:
 * - binding RxProperties to rx.Observables
 * - binding RxProperties to the view using data-binding
 * - the VM also demonstrates the use of shared rx.Observables to avoid duplicate processing when
 *   subscribing multiple times to the same source.
 */
public class RxPropertyExampleActivity extends MvvmActivity {

    private ActivityRxPropertyExampleBinding binding;

    @Inject
    RxPropertyExampleViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RxMvvmApp.getAppComponent()
                .plusActivity(new ActivityModule(this))
                .inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rx_property_example);
        binding.setVm(viewModel);
    }

    @Override
    protected ViewModel getViewModel() {
        return viewModel;
    }
}
