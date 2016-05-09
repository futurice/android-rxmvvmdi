package com.futurice.rxmvvmdi.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.futurice.rxmvvmdi.R;
import com.futurice.rxmvvmdi.RxMvvmApp;
import com.futurice.rxmvvmdi.dagger.components.ActivityComponent;
import com.futurice.rxmvvmdi.dagger.modules.ActivityModule;
import com.futurice.rxmvvmdi.databinding.ActivityRxBindingExampleBinding;
import com.futurice.rxmvvmdi.viewmodels.RxBindingExampleViewModel;
import com.futurice.rxmvvmdi.viewmodels.ViewModel;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * This activity introduces the following concepts:
 * - binding rx.Observables from the VM manually in the view layer to view components (without
 *   declarative data-binding)
 * - using a base MvvmActivity to assist in controlling lifecycle events of the view and VM.
 * - the importance of understanding schedulers and their effect on Observable streams via
 *   observerOn and subscribeOn. (Slightly modifying the streams in the view and VM will cause a
 *   crash and/or main thread hangs when calling vm.calculate(), which can then be solved).
 */
public class RxBindingExampleActivity extends MvvmActivity {

    private ActivityRxBindingExampleBinding binding;

    @Inject
    RxBindingExampleViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityComponent component = RxMvvmApp.getAppComponent().plusActivity(new ActivityModule(this));
        component.inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rx_binding_example);
    }

    @Override
    protected ViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected void bindView(CompositeSubscription subscriptions) {
        subscriptions.add(viewModel
                .getTimeStream()
                .subscribe(time -> binding.timeText.setText(time)));

        subscriptions.add(viewModel
                .getHighLoadStream()
                .subscribe(iteration -> binding.iterationsText.setText(iteration.toString())));
    }
}
