package vn.tiki.test_tiki.ui.core;

/**
 * Created by nhat on 3/2/19
 */
public interface IBaseActivity {

    void onViewActive();

    void onViewInactive();

    void onError(Throwable throwable);
}