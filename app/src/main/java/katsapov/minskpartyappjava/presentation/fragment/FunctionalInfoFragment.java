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
import katsapov.minskpartyappjava.domain.infoscreen.FunctionsItemInfoInteractor;
import katsapov.minskpartyappjava.presentation.PictureMvpView;
import katsapov.minskpartyappjava.presentation.adapter.InfoScreenAdapter;
import katsapov.minskpartyappjava.presentation.base.ItemClickListenerPresenter;
import katsapov.minskpartyappjava.presentation.common.SnapRecyclerItemHelper;
import katsapov.minskpartyappjava.presentation.widget.ItemOffsetDecoration;


public class FunctionalInfoFragment extends Fragment implements PictureMvpView, ItemClickListenerPresenter {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    private FunctionsItemInfoInteractor functionsInfoInteractor;
    private SnapRecyclerItemHelper snapRecyclerItemHelper = new SnapRecyclerItemHelper();

    @Nullable
    @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, rootView);

        functionsInfoInteractor = new FunctionsItemInfoInteractor();
        functionsInfoInteractor.attachedView(this);
        setupRecyclerView();

        return rootView;
    }

    @Override public void onResume() {
        super.onResume();
        functionsInfoInteractor.onResume();
    }

    @Override public void setItems(ArrayList<Picture> pictureList) {
        RecyclerView.Adapter adapter = getAdapter(pictureList);
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
        functionsInfoInteractor.detachView();
        super.onDestroy();
    }

    @Override
    public void onItemClickListener(int position) {
        functionsInfoInteractor.onItemSelected(position);
    }

    private void setupRecyclerView() {
        getLayoutManager();
        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.addItemDecoration(new ItemOffsetDecoration(recyclerView.getContext(), R.dimen.item_decoration));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        snapRecyclerItemHelper.attachToRecyclerView(recyclerView);
    }

    private int getLayout() {
        return R.layout.fragment_base;
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        return getLinearLayoutManager();
    }

    private RecyclerView.Adapter getAdapter(ArrayList<Picture> pictureList) {
        return new InfoScreenAdapter(pictureList, R.layout.functional_item);
    }

    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(
                getActivity(),
                RecyclerView.VERTICAL,
                false);
    }
}
