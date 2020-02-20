package katsapov.minskpartyappjava.presentation.base;


public interface BasePresenter<V extends BaseView> {
    void attachedView(V view);
    void detachView();
}