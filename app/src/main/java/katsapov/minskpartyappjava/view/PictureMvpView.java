package katsapov.minskpartyappjava.view;

import java.util.ArrayList;
import katsapov.minskpartyappjava.model.Picture;

/**
 * Интерфейс работы view
 *
 * setItems - помещение ячеек с информацией в адаптер
 * showMessage - сообщение при обработке различных ситуаций
 *
 * @author Katsapov Sergey
 *
 */
public interface PictureMvpView {
    void setItems(ArrayList<Picture> pictureList);
    void showProgress();
    void hideProgress();
    void showMessage(String message);
}
