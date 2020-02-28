package katsapov.minskpartyappjava.party_info_details;


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
    public void requestMovieData() {
        if(partyInfoView != null){
            partyInfoView.showProgress();
        }
        partyInfoModel.getPartyInfoDetails(this);
    }

    @Override
    public void onFinished(Feed feed) {
        if(partyInfoView != null){
            partyInfoView.hideProgress();
        }
        partyInfoView.setDataToViews(feed);
    }


    @Override
    public void onFailure(Throwable t) {
        if(partyInfoView != null){
            partyInfoView.hideProgress();
        }
        partyInfoView.onResponseFailure(t);
    }
}