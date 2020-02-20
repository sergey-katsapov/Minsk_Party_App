package katsapov.minskpartyappjava.domain.authorization.signup;

import katsapov.minskpartyappjava.presentation.SignUpView;
import katsapov.minskpartyappjava.presentation.base.BasePresenter;

public interface SignUpPresenter extends BasePresenter<SignUpView> {
    void signUp(String email, String password);
}