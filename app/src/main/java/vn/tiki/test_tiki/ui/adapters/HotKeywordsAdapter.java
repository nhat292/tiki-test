package vn.tiki.test_tiki.ui.adapters;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tiki.test_tiki.R;
import vn.tiki.test_tiki.model.HotKeyword;

/**
 * Created by nhat on 3/2/19
 */
public class HotKeywordsAdapter extends RecyclerView.Adapter<HotKeywordsAdapter.HotKeywordsViewHolder> {

    private List<HotKeyword> mItems;

    public HotKeywordsAdapter(List<HotKeyword> items) {
        mItems = items;
    }

    @NonNull
    @Override
    public HotKeywordsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot_keyword, parent, false);
        return new HotKeywordsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HotKeywordsViewHolder hotKeywordsViewHolder, int position) {
        HotKeyword keyword = mItems.get(position);
        hotKeywordsViewHolder.setHotKeyword(keyword);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class HotKeywordsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_hot_keyword)
        TextView mTxtHotKeyword;

        HotKeywordsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setHotKeyword(HotKeyword hotKeyword) {
            // Set text
            mTxtHotKeyword.setText(hotKeyword.getKeyword());
            // Update background color
            Drawable background = mTxtHotKeyword.getBackground();
            if (background instanceof GradientDrawable) {
                GradientDrawable gradientDrawable = (GradientDrawable) background;
                gradientDrawable.setColor(hotKeyword.getColor());
            }
        }
    }
}
