package katsapov.minskpartyappjava.model;

import java.util.ArrayList;

/**
 * Интерфейс итоговой загрузки листа картинок
 *
 * @author Katsapov Sergey
 *
 */
public interface LoaderListener {
    void onFinished(ArrayList<Picture> pictureList);
}
