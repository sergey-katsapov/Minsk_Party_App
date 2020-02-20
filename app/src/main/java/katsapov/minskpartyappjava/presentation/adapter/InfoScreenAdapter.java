package katsapov.minskpartyappjava.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import katsapov.minskpartyappjava.R;
import katsapov.minskpartyappjava.data.entities.Picture;
import katsapov.minskpartyappjava.presentation.base.ItemClickListenerPresenter;

public class InfoScreenAdapter extends RecyclerView.Adapter<InfoScreenAdapter.ExampleHolder> {

    private ArrayList<Picture> pictureArrayList;
    private int itemLayout;
    private ItemClickListenerPresenter itemClickListenerPresenter;

    public void setItemClickListenerPresenter(ItemClickListenerPresenter itemClickListenerPresenter) {
        this.itemClickListenerPresenter = itemClickListenerPresenter;
    }

    public InfoScreenAdapter(ArrayList<Picture> pictureArrayList, int itemLayout) {
        this.pictureArrayList = pictureArrayList;
        this.itemLayout = itemLayout;
    }

    @NonNull
    @Override
    public ExampleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ExampleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ExampleHolder holder, final int position) {
        final Picture picture = pictureArrayList.get(position);
        holder.title.setText(picture.getName());
        holder.imageView.setImageResource(picture.getImage());
    }

    @Override
    public int getItemCount() {
        return pictureArrayList.size();
    }

    public class ExampleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txt_title)
        TextView title;
        @BindView(R.id.imageView)
        ImageView imageView;

        ExampleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListenerPresenter != null)
                itemClickListenerPresenter.onItemClickListener(getAdapterPosition());
        }
    }
}
