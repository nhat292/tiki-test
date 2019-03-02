package vn.tiki.test_tiki.ui.activities.main;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import vn.tiki.test_tiki.R;
import vn.tiki.test_tiki.TikiApp;
import vn.tiki.test_tiki.model.HotKeyword;
import vn.tiki.test_tiki.network.Manager;
import vn.tiki.test_tiki.ui.adapters.HotKeywordsAdapter;
import vn.tiki.test_tiki.ui.core.BaseActivity;
import vn.tiki.test_tiki.utils.RecyclerViewItemDecoration;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    @Inject
    Manager mManager;

    @BindView(R.id.txt_hot_keywords)
    TextView mTxtHotKeywords;
    @BindView(R.id.recycler_view_hot_keywords)
    RecyclerView mRecyclerViewHotKeywords;
    @BindView(R.id.txt_hot_keywords_empty)
    TextView mTxtHotKeywordsEmpty;

    private List<HotKeyword> mHotKeywords = new ArrayList<>();
    private HotKeywordsAdapter mHotKeywordsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUnbinder();
        mTxtHotKeywords.setVisibility(View.INVISIBLE);
        mTxtHotKeywordsEmpty.setVisibility(View.INVISIBLE);

        TikiApp.get(MainActivity.this).getApplicationComponent().inject(this);
        setPresenter(new MainPresenter(this, this, mManager));

        presenter.onViewActive();

        // Init HotKeywordsAdapter
        mHotKeywordsAdapter = new HotKeywordsAdapter(mHotKeywords);

        // Set adapter
        mRecyclerViewHotKeywords.addItemDecoration(new RecyclerViewItemDecoration());
        mRecyclerViewHotKeywords.setAdapter(mHotKeywordsAdapter);

        // Get hot keywords from API
        presenter.getHotKeywords();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewInactive();
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    @Override
    public void onGetHotKeywordsSuccess(List<HotKeyword> hotKeywords) {
        mHotKeywords.clear();
        mHotKeywords.addAll(hotKeywords);
        mHotKeywordsAdapter.notifyDataSetChanged();

        if (mHotKeywords.size() == 0) {
            // Hide hot keywords text if needed
            if (mTxtHotKeywords.getVisibility() == View.VISIBLE) {
                mTxtHotKeywords.setVisibility(View.INVISIBLE);
            }
            // Show empty keywords text if needed
            if (mTxtHotKeywordsEmpty.getVisibility() == View.INVISIBLE) {
                mTxtHotKeywordsEmpty.setVisibility(View.VISIBLE);
            }
        } else {
            // Show hot keywords text if needed
            if (mTxtHotKeywords.getVisibility() == View.INVISIBLE) {
                mTxtHotKeywords.setVisibility(View.VISIBLE);
            }
            // Hide empty keywords text if needed
            if (mTxtHotKeywordsEmpty.getVisibility() == View.VISIBLE) {
                mTxtHotKeywordsEmpty.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onGetHotKeywordsFailure(String error) {
        showMessageDialog(getString(R.string.error), error);
    }
}
