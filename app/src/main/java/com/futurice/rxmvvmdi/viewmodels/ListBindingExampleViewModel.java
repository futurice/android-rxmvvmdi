package com.futurice.rxmvvmdi.viewmodels;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.futurice.rxmvvmdi.models.Post;
import com.futurice.rxmvvmdi.services.IBackendService;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by osal on 16.5.2016.
 */
public class ListBindingExampleViewModel extends ViewModel {

    public final ObservableList<Post> posts;

    private final IBackendService backendService;

    @Inject
    public ListBindingExampleViewModel(@NonNull final IBackendService backendService) {
        this.backendService = backendService;
        posts = new ObservableArrayList<>();
    }

    @Override
    protected void subscribe(@NonNull CompositeSubscription subscriptions) {
        subscriptions.add(backendService
                .getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newPosts -> {
                    posts.clear();
                    posts.addAll(newPosts);
                })
        );
    }
}
