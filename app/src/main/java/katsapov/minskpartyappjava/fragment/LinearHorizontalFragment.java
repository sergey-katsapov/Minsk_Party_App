package katsapov.minskpartyappjava.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import katsapov.minskpartyappjava.R;
import katsapov.minskpartyappjava.adapter.AdapterExample;
import katsapov.minskpartyappjava.model.Picture;

/**
 * Фрагмент с горизонтальный recycler
 *
 * @author Katsapov Sergey
 *
 */
public class LinearHorizontalFragment extends BaseFragment {

    public static LinearHorizontalFragment newInstance() {
        return new LinearHorizontalFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_base;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return getLinearLayoutManager();
    }

    @Override
    protected RecyclerView.Adapter getAdapter(ArrayList<Picture> pictureList) {
        return new AdapterExample(pictureList, R.layout.item_type_two);
    }

    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);
    }
}

