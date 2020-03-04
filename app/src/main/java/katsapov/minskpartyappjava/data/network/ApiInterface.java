package katsapov.minskpartyappjava.data.network;

import io.reactivex.Single;
import katsapov.minskpartyappjava.data.entities.partyInfo.Feed;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiInterface {

    @Headers("Content-Type: application/json")
    @GET("new.json")
    Single<Feed> getData();
}