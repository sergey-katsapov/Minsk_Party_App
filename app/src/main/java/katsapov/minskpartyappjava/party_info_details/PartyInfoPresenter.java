package katsapov.minskpartyappjava.party_info_details;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static katsapov.minskpartyappjava.party_info_details.FeedDataMapper.getListOfFeed;

public class PartyInfoPresenter implements PartyInfoContract.Presenter {

    private PartyInfoContract.View partyInfoView;
    private PartyInfoContract.Model partyInfoInteractor;

    public PartyInfoPresenter(PartyInfoContract.View partyInfolView) {
        this.partyInfoView = partyInfolView;
        this.partyInfoInteractor = new PartyInfoInteractor();
    }

    @Override
    public void onDestroy() {
        partyInfoView = null;
    }

    @Override
    public void getPartyInfoData() {
        if (partyInfoView != null) {
            partyInfoView.showProgress();
        }
        partyInfoInteractor.getPartyInfoDetails()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(feeds -> {
                    partyInfoView.setData(getListOfFeed(feeds));
                    partyInfoView.hideProgress();
                }, Throwable::printStackTrace);
    }
}