package katsapov.minskpartyappjava.fragment;

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
import katsapov.minskpartyappjava.adapter.AdapterExample;
import katsapov.minskpartyappjava.model.Picture;
import katsapov.minskpartyappjava.presenter.FunctionsPicturePresenter;
import katsapov.minskpartyappjava.presenter.RecyclerItemClickListener;
import katsapov.minskpartyappjava.view.PictureMvpView;
import katsapov.minskpartyappjava.widget.ItemOffsetDecoration;


public class FunctionalInfoFragment extends Fragment implements PictureMvpView, RecyclerItemClickListener {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    private FunctionsPicturePresenter functionsPicturePresenter;

    @Nullable
    @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, rootView);

        functionsPicturePresenter = new FunctionsPicturePresenter();
        functionsPicturePresenter.attachedView(this);
        setupRecyclerView();

        return rootView;
    }

    @Override public void onResume() {
        super.onResume();
        functionsPicturePresenter.onResume();
    }

    @Override public void setItems(ArrayList<Picture> pictureList) {
        RecyclerView.Adapter adapter = getAdapter(pictureList);
        recyclerView.setAdapter(adapter);

        if(adapter instanceof AdapterExample)
            ((AdapterExample) adapter).setRecyclerItemClickListener(this);
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
        functionsPicturePresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onItemClickListener(int position) {
        functionsPicturePresenter.onItemSelected(position);
    }

    private void setupRecyclerView() {
        getLayoutManager();
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

    private RecyclerView.Adapter getAdapter(ArrayList<Picture> pictureList) {
        return new AdapterExample(pictureList, R.layout.functional_item);
    }

    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(
                getActivity(),
                RecyclerView.VERTICAL,
                false);
    }
}
