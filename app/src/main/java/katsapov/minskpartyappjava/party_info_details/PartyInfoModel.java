package katsapov.minskpartyappjava.party_info_details;

import android.util.Log;

import katsapov.minskpartyappjava.data.entities.partyInfo.Feed;
import katsapov.minskpartyappjava.data.network.ApiClient;
import katsapov.minskpartyappjava.data.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @file PartyInfoModel.java
 * Это класс получения данных для  формирования моедели экрана мероприятий
 * @author Katsapov Sergey
 * @date 28.02.2020
 */
public class PartyInfoModel implements PartyInfoContract.Model {

    private final String TAG = "PartyInfoModel";

    @Override
    public void getPartyInfoDetails(final OnFinishedListener onFinishedListener) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<Feed> call = apiService.getData();
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Feed feed = response.body();
                Log.d(TAG, "Feed data: " + feed.toString());
                onFinishedListener.onFinished(feed);
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }
}