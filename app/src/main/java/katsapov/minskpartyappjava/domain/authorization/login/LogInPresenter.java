package katsapov.minskpartyappjava.domain.authorization.login;

import katsapov.minskpartyappjava.presentation.LoginView;
import katsapov.minskpartyappjava.presentation.base.BasePresenter;

public interface LogInPresenter extends BasePresenter<LoginView> {
    void login(String email, String password);
    void checkLogin();
}