package katsapov.minskpartyappjava.party_info_details;

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
            void onFailure(Throwable t);
        }
        void getPartyInfoDetails(OnFinishedListener onFinishedListener);
    }

    interface View {
        void showProgress();
        void hideProgress();
        void setDataToViews(Feed feed);
        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {
        void onDestroy();
        void requestMovieData();
    }
}