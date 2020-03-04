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

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import katsapov.minskpartyappjava.R;
import katsapov.minskpartyappjava.data.entities.partyInfo.Feed;
import katsapov.minskpartyappjava.data.entities.partyInfo.children.Children;
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

        partyInfoScreenAdapter = getAdapter(feedList);
        recyclerView.setAdapter(partyInfoScreenAdapter);
        setupRecyclerView();
        getPartyInfoData();

        return baseView;
    }

    /**
     * Получение Feed данных в вспомогательном потоке
     *
     * @author Katsapov Sergey
     */
    //TODO import in presenter
    @Override
    public void getAllFeedInfoRX(Feed feed) {
        getAllFeedInfo(feed)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(feeds -> {
                }, Throwable::printStackTrace);
    }

    @Override
    public void updateAdapter() {
        partyInfoScreenAdapter.notifyDataSetChanged();
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
    //TODO import in presenter
    private void getPartyInfoData() {
        partyInfoPresenter = new PartyInfoPresenter(this);
        partyInfoPresenter.getPartyInfoData();
    }

    /**
     * Получаем Feed и для него через .flatMap
     * берем данные с помощью функции
     * {@link  #getListOfFeed(Feed)}
     *
     * @author Katsapov Sergey
     * @date 04/03/2020
     */
    //TODO import in presenter
    private Observable<ArrayList<Feed>> getAllFeedInfo(Feed feed) {
        return Observable.just(feed)
                .doOnError(Throwable::printStackTrace)
                .flatMap(feed1 -> Observable.just(getListOfFeed(feed1)));
    }

    /**
     * Для каждого элемента Feed собираем его список полной информации
     *
     * @author Katsapov Sergey
     * * @date 04/03/2020
     */
    private ArrayList<Feed> getListOfFeed(Feed feed) {
        ArrayList<Children> listOfChildren = feed.getData().getChildren();
        ArrayList<Feed> listOfFeeds = new ArrayList<>();
        for (int i = 0; i < listOfChildren.size(); i++) {
            listOfFeeds.add(feed);
        }
        feedList.clear();
        feedList.addAll(listOfFeeds);
        return feedList;
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        return getLinearLayoutManager();
    }

    private PartyInfoScreenAdapter getAdapter(ArrayList<Feed> feedList) {
        return new PartyInfoScreenAdapter(this.getActivity(), feedList);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.addItemDecoration(new ItemOffsetDecoration(recyclerView.getContext(), R.dimen.item_decoration));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        snapRecyclerItemHelper.attachToRecyclerView(recyclerView);
    }

    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);
    }
}
