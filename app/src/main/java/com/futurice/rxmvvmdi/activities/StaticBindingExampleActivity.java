package com.futurice.rxmvvmdi.activities;

import android.app.Activity;
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

    @BindingAdapter("android:textColor")
    public static void setTextColor(TextView view, StaticBindingExampleViewModel.CoffeeType coffeeType) {
        switch (coffeeType) {
            case CAPRICCIO:
                view.setTextColor(Color.argb(0xFF, 0x3E, 0xE4, 0x00));
                break;
            case LIVANTO:
                view.setTextColor(Color.argb(0xFF, 0xFF, 0x99, 0x00));
                break;
            default:
                view.setTextColor(Color.BLACK);
                break;
        }
    }
}
