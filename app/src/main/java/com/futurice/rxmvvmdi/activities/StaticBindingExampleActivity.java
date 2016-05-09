package com.futurice.rxmvvmdi.activities;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.futurice.rxmvvmdi.R;
import com.futurice.rxmvvmdi.RxMvvmApp;
import com.futurice.rxmvvmdi.dagger.components.ActivityComponent;
import com.futurice.rxmvvmdi.dagger.modules.ActivityModule;
import com.futurice.rxmvvmdi.databinding.ActivityStaticBindingExampleBinding;
import com.futurice.rxmvvmdi.viewmodels.StaticBindingExampleViewModel;
import com.futurice.rxmvvmdi.viewmodels.ViewModel;

import javax.inject.Inject;

/**
 * This activity demonstrates static data-binding, introducing the following concepts:
 * - binding a VM and simple properties to the view using data-binding.
 * - VM property conversion to values suitable for displaying in the view using BindingAdapters.
 */
public class StaticBindingExampleActivity extends MvvmActivity {
    @Inject
    StaticBindingExampleViewModel viewModel;

    private ActivityStaticBindingExampleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityComponent component = RxMvvmApp.getAppComponent().plusActivity(new ActivityModule(this));
        component.inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_static_binding_example);
        binding.setVm(viewModel);
    }

    @Override
    protected ViewModel getViewModel() {
        return viewModel;
    }
}
