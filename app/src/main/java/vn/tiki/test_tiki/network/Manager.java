package vn.tiki.test_tiki.network;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nhat on 3/2/19
 */
public final class Manager implements ManagerService {

    ManagerService managerService;
    Context context;

    public Manager(Context context) {
        this.context = context;
        this.managerService = RestClient.getInstance(context, false).getManagerService();
    }

    public void resetApi() {
        this.managerService = RestClient.getInstance(context, true).getManagerService();
    }

    @Override
    public Observable<List<String>> getHotKeywords() {
        return managerService.getHotKeywords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
