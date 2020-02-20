package katsapov.minskpartyappjava.data.repository;

/**
 * Интерфейс загрузки листа картинок
 *
 * @author Katsapov Sergey
 *
 */
public interface InfoScreenRepository {
    void loadItems(LoaderListener loaderListener);
}