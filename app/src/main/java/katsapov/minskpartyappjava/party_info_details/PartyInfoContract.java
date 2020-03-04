package katsapov.minskpartyappjava.party_info_details;

import io.reactivex.Single;
import katsapov.minskpartyappjava.data.entities.partyInfo.Feed;

/**
 * @file PartyInfoContract.java
 * Это класс-контракт взаимодействия в PartyInfo Fragment
 * @author Katsapov Sergey
 * @date 28.02.2020
 */
public interface PartyInfoContract {

    interface Model {
        interface OnFinishedListener {
            void onFinished(Feed feed);
        }
        Single<Feed> getPartyInfoDetails();
    }

    interface View {
        void showProgress();
        void hideProgress();
        void getAllFeedInfoRX(Feed feed); //TODO presenter!
        void updateAdapter();
    }

    interface Presenter {
        void onDestroy();
        void getPartyInfoData();
    }
}