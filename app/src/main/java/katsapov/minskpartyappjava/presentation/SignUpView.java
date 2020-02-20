package katsapov.minskpartyappjava.presentation;

import katsapov.minskpartyappjava.presentation.base.BaseView;

public interface SignUpView extends BaseView {
    void showValidationError();
    void signUpSuccess();
    void signUpError();
    void setProgressVisibility(boolean visibility);
}
