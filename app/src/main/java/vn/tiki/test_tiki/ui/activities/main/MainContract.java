package vn.tiki.test_tiki.ui.activities.main;

import android.content.Intent;

import java.util.List;

import vn.tiki.test_tiki.model.HotKeyword;
import vn.tiki.test_tiki.ui.core.IBasePresenter;
import vn.tiki.test_tiki.ui.core.IBaseView;

/**
 * Created by nhat on 3/2/19
 */
public interface MainContract {

    interface View extends IBaseView<Presenter> {

        void showLoading();

        void hideLoading();

        void onGetHotKeywordsSuccess(List<HotKeyword> hotKeywords);

        void onGetHotKeywordsFailure(String error);
    }

    interface Presenter extends IBasePresenter {

        void getHotKeywords();
    }
}
