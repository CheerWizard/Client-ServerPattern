package com.example.mockup.mvvm.business_logic.network.webservices;

import com.example.mockup.mvvm.business_logic.data.EventResponse;
import com.example.mockup.mvvm.business_logic.data.Events4UserResponse;
import com.example.mockup.mvvm.business_logic.data.Match;
import com.example.mockup.mvvm.business_logic.data.User;
import com.example.mockup.mvvm.business_logic.data.UserResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MobileWebService {
    @GET("api/mobile/eventsForUser/{userId}")
    Single<Events4UserResponse> getAllEventsForUser(@Path("userId") final int userId);
    @POST("api/mobile/createUser")
    Single<UserResponse> postUser(@Body User user);
    @POST("api/mobile/createMatch")
    Single<EventResponse> postMatch(@Body Match match);
}
