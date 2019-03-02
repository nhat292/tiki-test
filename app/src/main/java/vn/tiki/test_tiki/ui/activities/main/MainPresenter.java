package vn.tiki.test_tiki.ui.activities.main;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import vn.tiki.test_tiki.model.HotKeyword;
import vn.tiki.test_tiki.network.Manager;
import vn.tiki.test_tiki.ui.core.BasePresenter;
import vn.tiki.test_tiki.utils.CommonUtils;

/**
 * Created by nhat on 3/2/19
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private Manager manager;
    private MainContract.View view;
    private Context context;

    public MainPresenter(Context context, MainContract.View view, Manager manager) {
        super(view);
        this.context = context;
        this.view = view;
        this.manager = manager;
    }

    @Override
    public void getHotKeywords() {
        view.showLoading();
        manager.getHotKeywords().subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addToAutoDisposeList(d);
            }

            @Override
            public void onNext(List<String> keywords) {
                List<HotKeyword> hotKeywords = new ArrayList<>();
                for (String k : keywords) {
                    hotKeywords.add(new HotKeyword(CommonUtils.breakKeywordIntoTwoLinesAlgorithm(k), CommonUtils.randomColor()));
                }
                view.hideLoading();
                view.onGetHotKeywordsSuccess(hotKeywords);
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoading();
                view.onGetHotKeywordsFailure(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
