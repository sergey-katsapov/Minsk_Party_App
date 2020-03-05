package katsapov.minskpartyappjava.party_info_details;

import io.reactivex.Single;
import katsapov.minskpartyappjava.data.entities.partyInfo.Feed;
import katsapov.minskpartyappjava.data.network.ApiClient;
import katsapov.minskpartyappjava.data.network.ApiInterface;

/**
 * Это класс получения данных для  формирования моедели экрана мероприятий
 * @author Katsapov Sergey
 * @date 28.02.2020
 */
public class PartyInfoInteractor implements PartyInfoContract.Model {

    @Override
    public Single<Feed> getPartyInfoDetails() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        return apiService.getData();
    }
}