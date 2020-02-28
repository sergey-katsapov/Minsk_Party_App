package katsapov.minskpartyappjava.presentation.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import katsapov.minskpartyappjava.R;
import katsapov.minskpartyappjava.data.entities.partyInfo.Feed;
import katsapov.minskpartyappjava.data.entities.partyInfo.children.Children;
import katsapov.minskpartyappjava.data.entities.partyInfo.children.Data;
import katsapov.minskpartyappjava.party_info_details.PartyInfoContract;
import katsapov.minskpartyappjava.party_info_details.PartyInfoPresenter;
import katsapov.minskpartyappjava.presentation.adapter.PartyInfoScreenAdapter;
import katsapov.minskpartyappjava.presentation.base.ItemClickListenerPresenter;
import katsapov.minskpartyappjava.presentation.common.SnapRecyclerItemHelper;
import katsapov.minskpartyappjava.presentation.widget.ItemOffsetDecoration;

public class PartyInfoFragment extends Fragment implements PartyInfoContract.View, ItemClickListenerPresenter {

    private RecyclerView recyclerView;
    private SnapRecyclerItemHelper snapRecyclerItemHelper = new SnapRecyclerItemHelper();
    private TextView tvInfoPartyItemTitle;
    private ImageView ivInfoPartyItem;
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
        tvInfoPartyItemTitle = itemView.findViewById(R.id.tv_info_party_item_title);
        ivInfoPartyItem = itemView.findViewById(R.id.iv_info_party_item);
        feedList = new ArrayList<>();

        partyInfoScreenAdapter = getAdapter(feedList);
        recyclerView.setAdapter(partyInfoScreenAdapter);

        setupRecyclerView();
        requestPartyInfoData();

        return baseView;
    }

    @Override
    public void setDataToViews(Feed feed) {
        if (feed != null) {
            ArrayList<Children> listOfChildren = feed.getData().getChildren();
            ArrayList<Feed> listOfFeeds = new ArrayList<Feed>();

            for (int i = 0; i < listOfChildren.size(); i++) {
                Children positionChild = listOfChildren.get(i);
                Data data = positionChild.getData();
                tvInfoPartyItemTitle.setText(data.getTitle());

                Glide.with(this)
                        .load(data.getThumbnail())
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                pbInfoPartyItemLoad.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                pbInfoPartyItemLoad.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .apply(new RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
                        .into(ivInfoPartyItem);
                listOfFeeds.add(feed);
            }
            feedList.clear();
            feedList.addAll(listOfFeeds);
            partyInfoScreenAdapter.notifyDataSetChanged();
        }
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
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClickListener(int position) { }

    private RecyclerView.LayoutManager getLayoutManager() {
        return getLinearLayoutManager();
    }

    private PartyInfoScreenAdapter getAdapter(ArrayList<Feed> feedList) {
        return new PartyInfoScreenAdapter(this.getActivity(), feedList);
    }

    /**
     * Получение информации с сервиса через презентер
     *
     * @author Katsapov Sergey
     */
    private void requestPartyInfoData() {
        partyInfoPresenter = new PartyInfoPresenter(this);
        partyInfoPresenter.requestMovieData();
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
