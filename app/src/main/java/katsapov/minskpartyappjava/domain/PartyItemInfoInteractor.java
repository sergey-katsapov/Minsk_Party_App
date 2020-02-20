package katsapov.minskpartyappjava.domain;

import java.util.ArrayList;

import katsapov.minskpartyappjava.data.entities.Picture;
import katsapov.minskpartyappjava.data.repository.LoaderListener;
import katsapov.minskpartyappjava.data.repository.PartyInfoScreenRepository;
import katsapov.minskpartyappjava.presentation.PictureMvpView;
import katsapov.minskpartyappjava.presentation.base.BasePresenter;

public class PartyItemInfoInteractor implements ItemInfoPresenter, BasePresenter<PictureMvpView>, LoaderListener {

    private PictureMvpView pictureMvpView;
    private final PartyInfoScreenRepository зartyInfoScreenRepository;

    public PartyItemInfoInteractor() {
        зartyInfoScreenRepository = new PartyInfoScreenRepository();
    }

    @Override
    public void attachedView(PictureMvpView view) {
        if (view == null)
            throw new IllegalArgumentException("You can't set a null view");
        pictureMvpView = view;
    }

    @Override public void detachView() {
        pictureMvpView = null;
    }

    @Override public void onResume() {
        pictureMvpView.showProgress();
        зartyInfoScreenRepository.loadItems(this);
    }

    @Override public void onItemSelected(int position) {
        pictureMvpView.showMessage(Integer.toString(position));
    }

    @Override public void onFinished(ArrayList<Picture> pictureList) {
        pictureMvpView.setItems(pictureList);
        pictureMvpView.hideProgress();
    }
}
