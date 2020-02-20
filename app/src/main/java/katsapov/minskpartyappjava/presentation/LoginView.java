package katsapov.minskpartyappjava.presentation;

import katsapov.minskpartyappjava.presentation.base.BaseView;

public interface LoginView extends BaseView {
    void showValidationError(String message);
    void loginSuccess();
    void loginError();
    void setProgressVisibility(boolean visibility);
    void isLogin(boolean isLogin);
}