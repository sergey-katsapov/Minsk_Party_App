package katsapov.minskpartyappjava.presentation.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import katsapov.minskpartyappjava.R;
import katsapov.minskpartyappjava.presentation.fragment.FunctionalInfoFragment;
import katsapov.minskpartyappjava.presentation.fragment.PartyInfoFragment;

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
        FragmentTransaction partyInfoFragmentTransaction = fragmentManager.beginTransaction();
        Fragment partyInfoFragment = new PartyInfoFragment();
        partyInfoFragmentTransaction.replace(R.id.frHorizontalInfoScreenList, partyInfoFragment, "party_info_fragment");
        partyInfoFragmentTransaction.commit();

        FragmentTransaction functionalInfoFragmentTransaction = fragmentManager.beginTransaction();
        Fragment functionalInfoFragment = new FunctionalInfoFragment();
        functionalInfoFragmentTransaction.replace(R.id.frLinearInfoScreenList, functionalInfoFragment, "functional_info_fragment");
        functionalInfoFragmentTransaction.commit();
    }

    private void setupViews() {
        setSupportActionBar(toolbar);
    }
}
