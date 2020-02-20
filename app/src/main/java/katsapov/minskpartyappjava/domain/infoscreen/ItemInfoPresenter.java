package katsapov.minskpartyappjava.domain.infoscreen;

/**
 * Интерфейс работы при выборе info_item
 *
 * onItemSelected - обработка при выборе ячейки
 *
 * @author Katsapov Sergey
 *
 */
public interface ItemInfoPresenter {
    void onResume();
    void onItemSelected(int position);
}
