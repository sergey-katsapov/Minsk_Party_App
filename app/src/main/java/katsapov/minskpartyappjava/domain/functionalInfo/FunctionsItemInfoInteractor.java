package katsapov.minskpartyappjava.domain.functionalInfo;

import java.util.ArrayList;

import katsapov.minskpartyappjava.data.entities.Picture;
import katsapov.minskpartyappjava.data.repository.FunctionsInfoScreenRepository;
import katsapov.minskpartyappjava.data.repository.LoaderListener;
import katsapov.minskpartyappjava.domain.ItemInfoPresenter;
import katsapov.minskpartyappjava.presentation.PictureMvpView;
import katsapov.minskpartyappjava.presentation.base.BasePresenter;

public class FunctionsItemInfoInteractor implements ItemInfoPresenter, BasePresenter<PictureMvpView>, LoaderListener {

    private PictureMvpView pictureMvpView;
    private final FunctionsInfoScreenRepository functionsInfoScreenRepository;

    public FunctionsItemInfoInteractor() { functionsInfoScreenRepository = new FunctionsInfoScreenRepository(); }

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
        functionsInfoScreenRepository.loadItems(this);
    }

    /**
     * Функция обработки нажатия на item
     *
     * @param position - позиция item
     *
     */
    @Override public void onItemSelected(int position) {
        pictureMvpView.showMessage(Integer.toString(position));
    }

    @Override public void onFinished(ArrayList<Picture> pictureList) {
        pictureMvpView.setItems(pictureList);
        pictureMvpView.hideProgress();
    }
}
