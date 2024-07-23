package com.example.domainlogicandroidtest.data.api.retrofit;

import com.example.domainlogicandroidtest.data.api.GetUsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetUsersRetrofitRequest {
    @GET("/")
    Call<GetUsersResponse> getRandomUsers(@Query("results") int maxUsers, @Query("seed") int page);
}
