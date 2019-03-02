package vn.tiki.test_tiki.ui.core;

/**
 * Created by nhat on 3/2/19
 */
public interface IBaseView<T extends IBasePresenter> {

    void setPresenter(T presenter);
}
