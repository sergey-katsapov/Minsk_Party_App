package katsapov.minskpartyappjava.presenter;

import java.util.ArrayList;

import katsapov.minskpartyappjava.model.FunctionsPictureInteractor;
import katsapov.minskpartyappjava.model.LoaderListener;
import katsapov.minskpartyappjava.model.Picture;
import katsapov.minskpartyappjava.view.PictureMvpView;

public class FunctionsPicturePresenter implements Presenter<PictureMvpView>, LoaderListener {

    private PictureMvpView pictureMvpView;
    private final FunctionsPictureInteractor functionsPictureInteractor;

    public FunctionsPicturePresenter() {
        functionsPictureInteractor = new FunctionsPictureInteractor();
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
        functionsPictureInteractor.loadItems(this);
    }

    @Override public void onItemSelected(int position) {
        pictureMvpView.showMessage(Integer.toString(position));
    }

    @Override public void onFinished(ArrayList<Picture> pictureList) {
        pictureMvpView.setItems(pictureList);
        pictureMvpView.hideProgress();
    }
}
