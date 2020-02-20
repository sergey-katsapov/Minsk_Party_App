package katsapov.minskpartyappjava.presentation.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import katsapov.minskpartyappjava.R;
import katsapov.minskpartyappjava.domain.authorization.login.LogInInteractor;
import katsapov.minskpartyappjava.domain.authorization.login.LogInPresenter;
import katsapov.minskpartyappjava.presentation.LoginView;
import katsapov.minskpartyappjava.presentation.utils.Utils;

public class LoginActivity extends Activity implements LoginView {

    private FirebaseAuth auth ;
    private LogInPresenter loginPresenter ;

    @BindView(R.id.progressBar)
    ProgressBar progressBar ;
    @BindView(R.id.email)
    EditText email ;
    @BindView(R.id.password)
    EditText password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        auth = FirebaseAuth.getInstance() ;
        loginPresenter = new LogInInteractor(auth);

        loginPresenter.attachedView(this);
        loginPresenter.checkLogin();
    }

    @OnClick(R.id.btn_login) void onLoginButtonClick() {
        String email_text = email.getText().toString().trim();
        String password_text = password.getText().toString().trim();
        loginPresenter.login(email_text, password_text);
    }

    @OnClick(R.id.btn_signup) void onSignUpButtonClick() {
        Utils.setIntent(this, SignUpActivity.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }

    @Override
    public void showValidationError(String message) {
        Utils.showMessage(this, message);
    }

    @Override
    public void loginSuccess() {
        Utils.showMessage(this, "Login Success !");
        Utils.setIntent(this, InfoActivity.class);
    }

    @Override
    public void loginError() {
        Utils.showMessage(this, "Login Error ! ");
    }

    @Override
    public void setProgressVisibility(boolean visibility) {
        if (visibility)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    /**
     * Функция проверки вошел ли пользователь в приложение
     *
     * @param isLogin - позиция item
     * @return true/false
     *
     */
    @Override
    public void isLogin(boolean isLogin) {
        if (isLogin) {
            Utils.setIntent(this, InfoActivity.class);
            finish();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }
}
