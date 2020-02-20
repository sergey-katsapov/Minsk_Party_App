package katsapov.minskpartyappjava.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import katsapov.minskpartyappjava.R;
import katsapov.minskpartyappjava.data.entities.Picture;
import katsapov.minskpartyappjava.domain.infoscreen.PartyItemInfoInteractor;
import katsapov.minskpartyappjava.presentation.PictureMvpView;
import katsapov.minskpartyappjava.presentation.adapter.InfoScreenAdapter;
import katsapov.minskpartyappjava.presentation.base.ItemClickListenerPresenter;
import katsapov.minskpartyappjava.presentation.widget.ItemOffsetDecoration;

public class PartyInfoFragment extends Fragment implements PictureMvpView, ItemClickListenerPresenter {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    private PartyItemInfoInteractor partyInfoInteractor;
    private RecyclerView.Adapter adapter;

    @Nullable
    @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, rootView);

        partyInfoInteractor = new PartyItemInfoInteractor();
        partyInfoInteractor.attachedView(this);
        setupRecyclerView();

        return rootView;
    }

    @Override public void onResume() {
        super.onResume();
        partyInfoInteractor.onResume();
    }

    @Override public void setItems(ArrayList<Picture> pictureList) {
        adapter = getAdapter(pictureList);
        recyclerView.setAdapter(adapter);
        if(adapter instanceof InfoScreenAdapter)
            ((InfoScreenAdapter) adapter).setItemClickListenerPresenter(this);
    }

    @Override public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override public void onDestroy() {
        partyInfoInteractor.detachView();
        super.onDestroy();
    }

    @Override
    public void onItemClickListener(int position) {
        partyInfoInteractor.onItemSelected(position);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.addItemDecoration(new ItemOffsetDecoration(recyclerView.getContext(), R.dimen.item_decoration));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private int getLayout() {
        return R.layout.fragment_base;
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        return getLinearLayoutManager();
    }

    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);
    }

    private RecyclerView.Adapter getAdapter(ArrayList<Picture> pictureList) {
        return new InfoScreenAdapter(pictureList, R.layout.party_info_item);
    }
}
