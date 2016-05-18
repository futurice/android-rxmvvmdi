package com.futurice.rxmvvmdi.viewmodels;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.futurice.rxmvvmdi.models.Post;
import com.futurice.rxmvvmdi.services.IBackendService;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
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
        // Demonstrates the dynamic nature of the collection data binding by adding new items
        // to the list every itemAddDelayMs.
        final long itemAddDelayMs = 200;
        subscriptions.add(backendService
                .getPosts()
                .flatMap(posts -> {
                    return Observable.from(posts)
                            .startWith((Post)null)
                            .zipWith(Observable.interval(itemAddDelayMs, TimeUnit.MILLISECONDS), (post, time) -> post);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(post -> {
                    if (post == null) {
                        posts.clear();
                    }
                    else {
                        posts.add(post);
                    }
                })
        );
    }
}
