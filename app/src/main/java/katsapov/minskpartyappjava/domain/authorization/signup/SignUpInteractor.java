package katsapov.minskpartyappjava.domain.authorization.signup;

import android.app.Activity;
import android.text.TextUtils;

import com.google.firebase.auth.FirebaseAuth;

import katsapov.minskpartyappjava.presentation.SignUpView;


public class SignUpInteractor implements SignUpPresenter {

    private SignUpView signUpView ;
    private FirebaseAuth auth ;

    public SignUpInteractor(FirebaseAuth auth) {
        this.auth = auth ;
    }

    /**
     * Функция регистрации нового пользователя в приложении
     * @param email - электронный адрес пользователя
     * @param password  - пароль пользователя
     */
    @Override
    public void signUp(String email, String password) {
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ) {
            signUpView.showValidationError();
        } else {

            signUpView.setProgressVisibility(true);
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) signUpView, task -> {
                        signUpView.setProgressVisibility(false);

                        if (!task.isSuccessful()) {
                            signUpView.signUpError();
                        } else {
                            signUpView.signUpSuccess();
                        }
                    });
        }
    }

    @Override
    public void attachedView(SignUpView view) {
        signUpView = view ;
    }

    @Override
    public void detachView() {
        signUpView = null ;
    }
}