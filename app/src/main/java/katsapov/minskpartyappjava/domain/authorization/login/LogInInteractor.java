package katsapov.minskpartyappjava.domain.authorization.login;

import android.app.Activity;
import android.text.TextUtils;

import com.google.firebase.auth.FirebaseAuth;

import katsapov.minskpartyappjava.presentation.LoginView;


public class LogInInteractor implements LogInPresenter {

    private FirebaseAuth auth ;
    private LoginView loginView ;

    public LogInInteractor(FirebaseAuth auth) {
        this.auth = auth;
    }

    /**
     * Функция входа залогиненного пользователя
     * @param email - электронный адрес пользователя
     * @param password  - пароль пользователя
     */
    @Override
    public void login(String email, String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            loginView.showValidationError("Email and password can't be empty");
        } else {
            loginView.setProgressVisibility(true);

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) loginView, task -> {
                        loginView.setProgressVisibility(false);
                        if(!task.isSuccessful()) {
                            loginView.loginError();
                        } else {
                            loginView.loginSuccess();
                        }
                    });
        }
    }

    /**
     * Функция проверки регистрации пользователя
     */
    @Override
    public void checkLogin() {
        if (auth.getCurrentUser() != null)
            loginView.isLogin(true);
        else
            loginView.isLogin(false);
    }

    @Override
    public void attachedView(LoginView view) {
        loginView = view ;
    }

    @Override
    public void detachView() {
        loginView = null ;
    }
}
