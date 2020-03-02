package katsapov.minskpartyappjava.presentation.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import katsapov.minskpartyappjava.R;
import katsapov.minskpartyappjava.data.entities.partyInfo.Feed;
import katsapov.minskpartyappjava.data.entities.partyInfo.children.Children;
import katsapov.minskpartyappjava.data.entities.partyInfo.children.Data;


public class PartyInfoScreenAdapter extends RecyclerView.Adapter<PartyInfoScreenAdapter.PartyViewHolder> {

    private Context mContext;
    private ArrayList<Feed> feedList;

    public PartyInfoScreenAdapter(Context mContext, ArrayList<Feed> feedList) {
        this.mContext = mContext;
        this.feedList = feedList;
    }

    @NonNull
    @Override
    public PartyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.party_info_item, parent, false);
        return new PartyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PartyViewHolder holder, final int position) {

        Feed feed = feedList.get(position);
        ArrayList<Children> listOfChildren = feed.getData().getChildren();
        Children currentChild = listOfChildren.get(position);
        Data data = currentChild.getData();
        holder.tvInfoPartyItemTitle.setText(data.getTitle());

        Glide.with(mContext)
                .load(data.getThumbnail())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.pbLoadInfoPartyItem.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.pbLoadInfoPartyItem.setVisibility(View.GONE);
                        return false;
                    }
                })
                .apply(new RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(holder.ivInfoPartyItem);
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

     class PartyViewHolder extends RecyclerView.ViewHolder {

        TextView tvInfoPartyItemTitle;
        ImageView ivInfoPartyItem;
        ProgressBar pbLoadInfoPartyItem;

         PartyViewHolder(View itemView) {
            super(itemView);
            tvInfoPartyItemTitle = itemView.findViewById(R.id.tv_info_party_item_title);
            ivInfoPartyItem = itemView.findViewById(R.id.iv_info_party_item);
            pbLoadInfoPartyItem = itemView.findViewById(R.id.pb_load_info_party_item);
        }
    }
}