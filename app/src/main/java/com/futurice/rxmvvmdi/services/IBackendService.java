package com.futurice.rxmvvmdi.services;

import com.futurice.rxmvvmdi.models.Post;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface IBackendService {
    @GET("posts")
    Observable<List<Post>> getPosts();
}
