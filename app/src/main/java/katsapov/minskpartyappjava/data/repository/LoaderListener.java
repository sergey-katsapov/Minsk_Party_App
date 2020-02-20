package katsapov.minskpartyappjava.data.repository;

import java.util.ArrayList;

import katsapov.minskpartyappjava.data.entities.Picture;

/**
 * Интерфейс подтверждения окончания загрузки листа картинок
 *
 * @author Katsapov Sergey
 *
 */
public interface LoaderListener {
    void onFinished(ArrayList<Picture> pictureList);
}
