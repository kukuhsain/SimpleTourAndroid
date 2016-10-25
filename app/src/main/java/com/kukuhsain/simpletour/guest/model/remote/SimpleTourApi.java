package com.kukuhsain.simpletour.guest.model.remote;

import com.kukuhsain.simpletour.guest.model.pojo.Destination;
import com.kukuhsain.simpletour.guest.model.pojo.Package;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by kukuh on 15/10/16.
 */

public class SimpleTourApi {
    public static final String BASE_URL = "http://simple-tour.appspot.com";
//    public static final String BASE_URL = "http://122cdcb4.ngrok.io";
    private static SimpleTourApi INSTANCE;
    private static ApiEndpoint api;

    private SimpleTourApi() {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();
        api = retrofit.create(ApiEndpoint.class);
    }

    public static SimpleTourApi getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SimpleTourApi();
        }
        return INSTANCE;
    }

    public Observable<List<Destination>> getDestinations() {
        return api.getDestinations();
    }

    public Observable<List<Package>> getPackages(long destinationId) {
        return api.getPackages(destinationId);
    }

    private interface ApiEndpoint {
        @GET("/destinations")
        Observable<List<Destination>> getDestinations();

        @GET("/destination/{destination_id}/packages")
        Observable<List<Package>> getPackages(@Path("destination_id") long destinationId);
    }
}
