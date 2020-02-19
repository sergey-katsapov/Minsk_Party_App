package katsapov.minskpartyappjava.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import katsapov.minskpartyappjava.R;
import katsapov.minskpartyappjava.fragment.FunctionalInfoFragment;
import katsapov.minskpartyappjava.fragment.PartyInfoFragment;

public class InfoActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);
        ButterKnife.bind(this);
        setupViews();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction partyTransaction = fragmentManager.beginTransaction();
        Fragment partyFragment = new PartyInfoFragment();
        partyTransaction.replace(R.id.frHorizontalInfoList, partyFragment, "party_fragment");
        partyTransaction.commit();

        FragmentTransaction functionalTransaction = fragmentManager.beginTransaction();
        Fragment functionalFragment = new FunctionalInfoFragment();
        functionalTransaction.replace(R.id.frLinearInfoList, functionalFragment, "functional_fragment");
        functionalTransaction.commit();
    }

    private void setupViews() {
        setSupportActionBar(toolbar);
    }
}
