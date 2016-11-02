package com.kukuhsain.simpletour.guest.model.remote;

import com.google.gson.JsonObject;
import com.kukuhsain.simpletour.guest.model.local.PreferencesHelper;
import com.kukuhsain.simpletour.guest.model.pojo.Destination;
import com.kukuhsain.simpletour.guest.model.pojo.Package;
import com.kukuhsain.simpletour.guest.model.pojo.Reservation;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by kukuh on 15/10/16.
 */

public class SimpleTourApi {
    public static final String BASE_URL = "http://simple-tour.appspot.com";
//    public static final String BASE_URL = "http://3f4174c2.ngrok.io";
    private static SimpleTourApi INSTANCE;
    private static ApiEndpoint api;
    private static String accessToken;

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
        accessToken = PreferencesHelper.getInstance().getAccessToken();
        return INSTANCE;
    }

    public Observable<String> register(String name, String email, String password, String phone) {
        return api.register(name, email, password, phone)
                .map(jsonObject -> {
                    String accessToken = jsonObject.get("accessToken").getAsString();
                    return accessToken;
                });
    }

    public Observable<String> login(String email, String password) {
        return api.login(email, password)
                .map(jsonObject -> {
                    String accessToken = jsonObject.get("accessToken").getAsString();
                    return accessToken;
                });
    }

    public Observable<List<Destination>> getDestinations() {
        return api.getDestinations();
    }

    public Observable<List<Package>> getPackages(long destinationId) {
        return api.getPackages(destinationId);
    }

    public Observable<Reservation> bookPackage(long packageId, int numberOfPeople) {
        return api.bookPackage(accessToken, packageId, numberOfPeople);
    }

    private interface ApiEndpoint {
        @FormUrlEncoded
        @POST("/guest/register")
        Observable<JsonObject> register(@Field("name") String name,
                                        @Field("email") String email,
                                        @Field("password") String password,
                                        @Field("phone") String phone);

        @FormUrlEncoded
        @POST("/guest/login")
        Observable<JsonObject> login(@Field("email") String email,
                                     @Field("password") String password);

        @GET("/destinations")
        Observable<List<Destination>> getDestinations();

        @GET("/destination/{destination_id}/packages")
        Observable<List<Package>> getPackages(@Path("destination_id") long destinationId);

        @FormUrlEncoded
        @POST("/reservation/add")
        Observable<Reservation> bookPackage(@Field("access_token") String accessToken,
                                            @Field("package_id") long packageId,
                                            @Field("number_of_people") int numberOfPeople);
    }
}
