package katsapov.minskpartyappjava.presentation;

import java.util.ArrayList;

import katsapov.minskpartyappjava.data.entities.Picture;
import katsapov.minskpartyappjava.presentation.base.BaseView;

/**
 * Интерфейс работы view
 *
 * setItems - помещение ячеек с информацией в адаптер
 * showMessage - сообщение при обработке различных ситуаций
 *
 * @author Katsapov Sergey
 *
 */
public interface PictureMvpView extends BaseView {
    void setItems(ArrayList<Picture> pictureList);
    void showProgress();
    void hideProgress();
    void showMessage(String message);
}
