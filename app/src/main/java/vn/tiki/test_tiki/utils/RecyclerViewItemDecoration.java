package vn.tiki.test_tiki.utils;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by nhat on 3/2/19
 */
public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);

        Resources r = parent.getResources();

        int ten = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics()));
        int five = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, r.getDisplayMetrics()));

        outRect.top = five;
        outRect.bottom = five;
        if (position == 0) {
            outRect.left = ten;
            outRect.right = five;
        } else if (position == parent.getAdapter().getItemCount() - 1) {
            outRect.left = five;
            outRect.right = ten;
        } else {
            outRect.left = five;
            outRect.right = five;
        }
    }
}
