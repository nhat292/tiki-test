package vn.tiki.test_tiki.ui.core;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by nhat on 3/2/19
 */
public class BasePresenter<T extends IBaseView> implements IBasePresenter {

    protected final T view;

    private CompositeDisposable subscriptions;

    public BasePresenter(T view) {
        this.view = view;
        subscriptions = new CompositeDisposable();
    }

    @Override
    public void onViewActive() {

    }

    @Override
    public void onViewInactive() {
        disposeSubscriptions();
    }

    @Override
    public void onError(Throwable throwable) {

    }

    /**
     * A disposable added through this method will be automatically disposed when Presenter
     * is stopped.
     *
     * @param disposable The Disposable (subscription) to add
     */
    protected void addToAutoDisposeList(Disposable disposable) {
        subscriptions.add(disposable);
    }

    /**
     * Manually trigger clearing Rx Subscription added using {addToAutoDisposeList()}
     */
    protected void disposeSubscriptions() {
        subscriptions.clear();
    }
}
