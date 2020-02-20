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
import katsapov.minskpartyappjava.domain.authorization.signup.SignUpInteractor;
import katsapov.minskpartyappjava.presentation.SignUpView;
import katsapov.minskpartyappjava.presentation.utils.Utils;

public class SignUpActivity extends Activity implements SignUpView {

    @BindView(R.id.progressBar)
    ProgressBar progressBar ;
    @BindView(R.id.email)
    EditText email ;
    @BindView(R.id.password)
    EditText password ;

    private SignUpInteractor signUpInteractor ;

    private FirebaseAuth auth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        ButterKnife.bind(this);
        auth = FirebaseAuth.getInstance() ;
        signUpInteractor = new SignUpInteractor(auth);
        signUpInteractor.attachedView(this);
    }

    @OnClick(R.id.sign_up_button) void onSignUpButtonClick() {
        signUpInteractor.signUp(email.getText().toString().trim(), password.getText().toString().trim());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        signUpInteractor.detachView();
    }

    @Override
    public void showValidationError() {
        Utils.showMessage(this, "Cek email dan password");
    }

    @Override
    public void signUpSuccess() {
        Utils.showMessage(this, "Signup Sukses !");
        Utils.setIntent(this, LoginActivity.class);
    }

    @Override
    public void signUpError() {
        Utils.showMessage(this, "SignUp Gagal !");
    }

    @Override
    public void setProgressVisibility(boolean visibility) {
        if (visibility)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
