package katsapov.minskpartyappjava.presentation.common;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SnapRecyclerItemHelper extends LinearSnapHelper {

    private OrientationHelper mVerticalHelper, mHorizontalHelper;

    public SnapRecyclerItemHelper() { }

    @Override
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView)
            throws IllegalStateException {
        super.attachToRecyclerView(recyclerView);
    }

    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager,
                                              @NonNull View targetView) {
        int[] out = new int[2];

        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToStart(targetView, getHorizontalHelper(layoutManager));
        } else {
            out[0] = 0;
        }

        if (layoutManager.canScrollVertically()) {
            out[1] = distanceToStart(targetView, getVerticalHelper(layoutManager));
        } else {
            out[1] = 0;
        }
        return out;
    }

    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {

            if (layoutManager.canScrollHorizontally()) {
                return getStartView(layoutManager, getHorizontalHelper(layoutManager));
            } else {
                return getStartView(layoutManager, getVerticalHelper(layoutManager));
            }
        }
        return super.findSnapView(layoutManager);
    }

    /**
     * Функция, определяющая расстояние до старта item
     * @param targetView - вьюха
     * @param helper - параметр ориентации (VERTICAL, HORIZONTAL)
     * @return  значение дистанции между item по формуле
     * (начало представления - значение начальнуой позиции макета после добавления начального отступа (деление зависит от padding)
     */
    private int distanceToStart(View targetView, OrientationHelper helper) {
        return helper.getDecoratedStart(targetView) - helper.getStartAfterPadding()/2;
    }

    /**
     * Функция, которая определяет начало отображения вью
     * @param layoutManager - наше вью
     * @param helper - параметр ориентации (VERTICAL, HORIZONTAL)
     * @return ссылку на функцию, которая помогает нам выбрать режим отображения в завиисимости от ориентации вью
     */
    private View getStartView(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
        if (layoutManager instanceof LinearLayoutManager) {

            int firstChild = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            View child = layoutManager.findViewByPosition(firstChild);

            boolean isLastItem = ((LinearLayoutManager) layoutManager)
                    .findLastCompletelyVisibleItemPosition()
                    == layoutManager.getItemCount() - 1;

            if (firstChild == RecyclerView.NO_POSITION || isLastItem) {
                return null;
            }

            if (helper.getDecoratedEnd(child) >= helper.getDecoratedMeasurement(child) / 2
                    && helper.getDecoratedEnd(child) > 0) {
                return child;
            } else {
                if (((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition()
                        == layoutManager.getItemCount() - 1) {
                    return null;
                } else {
                    return layoutManager.findViewByPosition(firstChild + 1);
                }
            }
        }
        return super.findSnapView(layoutManager);
    }

    private OrientationHelper getVerticalHelper(RecyclerView.LayoutManager layoutManager) {
        if (mVerticalHelper == null) {
            mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return mVerticalHelper;
    }

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        if (mHorizontalHelper == null) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return mHorizontalHelper;
    }
}