package com.futurice.rxmvvmdi.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.futurice.rxmvvmdi.BR;
import com.futurice.rxmvvmdi.R;
import com.futurice.rxmvvmdi.RxMvvmApp;
import com.futurice.rxmvvmdi.dagger.modules.ActivityModule;
import com.futurice.rxmvvmdi.databinding.ActivityListBindingExampleBinding;
import com.futurice.rxmvvmdi.viewmodels.ListBindingExampleViewModel;
import com.futurice.rxmvvmdi.viewmodels.ViewModel;

import javax.inject.Inject;

import me.tatarka.bindingcollectionadapter.ItemView;

public class ListBindingExampleActivity extends MvvmActivity {

    private ActivityListBindingExampleBinding binding;

    @Inject
    ListBindingExampleViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RxMvvmApp.getAppComponent()
                .plusActivity(new ActivityModule(this))
                .inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_binding_example);
        binding.setVm(viewModel);
        binding.setItemViews(new ItemViews());
    }

    @Override
    protected ViewModel getViewModel() {
        return viewModel;
    }

    public static class ItemViews {
        public final ItemView postItemView = ItemView.of(BR.item, R.layout.item_post);
    }
}
