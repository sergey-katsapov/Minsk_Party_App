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
import katsapov.minskpartyappjava.fragment.LinearHorizontalFragment;

public class InfoActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupViews();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment fragment = new LinearHorizontalFragment();
        ft.replace(R.id.container, fragment, "fragment");
        ft.commit();
    }

    private void setupViews() {
        setSupportActionBar(toolbar);
    }
}
