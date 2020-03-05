package katsapov.minskpartyappjava.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import katsapov.minskpartyappjava.R;
import katsapov.minskpartyappjava.data.entities.partyInfo.Feed;
import katsapov.minskpartyappjava.party_info_details.PartyInfoContract;
import katsapov.minskpartyappjava.party_info_details.PartyInfoPresenter;
import katsapov.minskpartyappjava.presentation.adapter.PartyInfoScreenAdapter;
import katsapov.minskpartyappjava.presentation.base.ItemClickListenerPresenter;
import katsapov.minskpartyappjava.presentation.common.SnapRecyclerItemHelper;
import katsapov.minskpartyappjava.presentation.widget.ItemOffsetDecoration;

public class PartyInfoFragment extends Fragment implements PartyInfoContract.View, ItemClickListenerPresenter {

    private RecyclerView recyclerView;
    private SnapRecyclerItemHelper snapRecyclerItemHelper = new SnapRecyclerItemHelper();
    private ArrayList<Feed> feedList;
    private PartyInfoPresenter partyInfoPresenter;
    private ProgressBar pbInfoPartyItemLoad;
    private PartyInfoScreenAdapter partyInfoScreenAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_base, container, false);
        View itemView = inflater.inflate(R.layout.party_info_item, container, false);

        recyclerView = baseView.findViewById(R.id.recycler_view);
        pbInfoPartyItemLoad = itemView.findViewById(R.id.pb_load_info_party_item);
        feedList = new ArrayList<>();
        partyInfoPresenter = new PartyInfoPresenter(this);

        partyInfoScreenAdapter = getAdapter(feedList);
        recyclerView.setAdapter(partyInfoScreenAdapter);
        initialize();
        getPartyInfoData();

        return baseView;
    }

    @Override
    public void setData(ArrayList<Feed> list) {
        partyInfoScreenAdapter.setData(list);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        partyInfoPresenter.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showProgress() {
        pbInfoPartyItemLoad.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        pbInfoPartyItemLoad.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }


    @Override
    public void onItemClickListener(int position) { }

    /**
     * Получение информации с сервиса через презентер
     *
     * @author Katsapov Sergey
     */
    private void getPartyInfoData() {
        partyInfoPresenter = new PartyInfoPresenter(this);
        partyInfoPresenter.getPartyInfoData();
    }

    private void initialize() {
        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.addItemDecoration(new ItemOffsetDecoration(recyclerView.getContext(), R.dimen.item_decoration));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        snapRecyclerItemHelper.attachToRecyclerView(recyclerView);
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

    private PartyInfoScreenAdapter getAdapter(ArrayList<Feed> feedList) {
        return new PartyInfoScreenAdapter(this.getActivity(), feedList);
    }
}
