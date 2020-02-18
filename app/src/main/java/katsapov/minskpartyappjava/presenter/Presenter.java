package katsapov.minskpartyappjava.presenter;

/**
 * Интерфейст работы презентера
 *
 * onItemSelected - обработка при выборе ячейки
 *
 * @author Katsapov Sergey
 *
 */
public interface Presenter <V> {
    void attachedView(V view);
    void detachView();
    void onResume();
    void onItemSelected(int position);
}
