package katsapov.minskpartyappjava.presentation.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import katsapov.minskpartyappjava.R;
import katsapov.minskpartyappjava.domain.authorization.logout.LogOutPresenter;
import katsapov.minskpartyappjava.presentation.fragment.FunctionalInfoFragment;
import katsapov.minskpartyappjava.presentation.fragment.PartyInfoFragment;
import katsapov.minskpartyappjava.presentation.utils.Utils;

public class InfoActivity extends AppCompatActivity implements LogOutPresenter {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private FirebaseAuth auth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);
        ButterKnife.bind(this);
        setupViews();

        auth = FirebaseAuth.getInstance() ;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction partyInfoFragmentTransaction = fragmentManager.beginTransaction();
        Fragment partyInfoFragment = new PartyInfoFragment();
        partyInfoFragmentTransaction.replace(R.id.frHorizontalInfoScreenList, partyInfoFragment, "party_info_fragment");
        partyInfoFragmentTransaction.commit();

        FragmentTransaction functionalInfoFragmentTransaction = fragmentManager.beginTransaction();
        Fragment functionalInfoFragment = new FunctionalInfoFragment();
        functionalInfoFragmentTransaction.replace(R.id.frLinearInfoScreenList, functionalInfoFragment, "functional_info_fragment");
        functionalInfoFragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_sign_out:
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupViews() {
        setSupportActionBar(toolbar);
    }

    @Override
    public void logOut() {
        auth.signOut();
        Utils.setIntent(this, LoginActivity.class);
    }
}
