package com.futurice.rxmvvmdi.services;

import com.futurice.rxmvvmdi.models.Post;
import com.futurice.rxmvvmdi.models.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface IBackendService {
    @GET("posts")
    Observable<List<Post>> getPosts();

    @GET("users/{userId}")
    Observable<User> getUser(@Path("userId") int userId);
}
