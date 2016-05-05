package com.futurice.rxmvvmdi.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.futurice.rxmvvmdi.R;
import com.futurice.rxmvvmdi.RxMvvmApp;
import com.futurice.rxmvvmdi.dagger.components.ActivityComponent;
import com.futurice.rxmvvmdi.dagger.modules.ActivityModule;
import com.futurice.rxmvvmdi.databinding.ActivityMainBinding;
import com.futurice.rxmvvmdi.viewmodels.MainViewModel;
import com.futurice.rxmvvmdi.viewmodels.ViewModel;

import javax.inject.Inject;

public class MainActivity extends MvvmActivity {
    private static final String TAG = MainActivity.class.getName();

    private ActivityMainBinding binding;

    @Inject
    RxMvvmApp app;

    @Inject
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityComponent component = RxMvvmApp.getAppComponent().plusActivity(new ActivityModule(this));
        component.inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandlers(new Handlers());
    }

    @Override
    protected ViewModel getViewModel() {
        return viewModel;
    }

    public class Handlers {
        public void onStaticDataBindingClicked(View view) {
            viewModel.selectStaticDataBinding();
        }

        public void onRxBindingClicked(View view) {
            viewModel.selectRxBinding();
        }

        public void onRxPropertyClicked(View view) {
            viewModel.selectRxProperty();
        }
    }
}
