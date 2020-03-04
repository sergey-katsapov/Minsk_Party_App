package katsapov.minskpartyappjava.party_info_details;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import katsapov.minskpartyappjava.R;
import katsapov.minskpartyappjava.data.entities.partyInfo.Feed;

public class PartyInfoPresenter implements PartyInfoContract.Presenter, PartyInfoContract.Model.OnFinishedListener {

    private PartyInfoContract.View partyInfoView;
    private PartyInfoContract.Model partyInfoModel;

    public PartyInfoPresenter(PartyInfoContract.View partyInfolView) {
        this.partyInfoView = partyInfolView;
        this.partyInfoModel = new PartyInfoModel();
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
        partyInfoModel.getPartyInfoDetails()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(feeds -> {
                    partyInfoView.updateAdapter();
                    onFinished(feeds);
                }, Throwable::printStackTrace);
    }

    @Override
    public void onFinished(Feed feed) {
        if (partyInfoView != null) {
            partyInfoView.hideProgress();
            partyInfoView.getAllFeedInfoRX(feed);
        } else {
            throw new RuntimeException(String.valueOf(R.string.view_not_found_exception));
        }
    }
}