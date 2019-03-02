package vn.tiki.test_tiki;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

import vn.tiki.test_tiki.di.component.ApplicationComponent;
import vn.tiki.test_tiki.di.component.DaggerApplicationComponent;
import vn.tiki.test_tiki.di.module.ContextModule;
import vn.tiki.test_tiki.di.module.ManagerModule;

/**
 * Created by nhat on 3/2/19
 */
public class TikiApp extends Application {

    private ApplicationComponent applicationComponent;

    private static WeakReference<Context> mContext;

    public static TikiApp get(Context context) {
        return (TikiApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = new WeakReference<>(getApplicationContext());
        initApplicationComponent();
    }

    /**
     * init application components
     */
    private void initApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .managerModule(new ManagerModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static Context getContext() {
        return mContext.get();
    }
}
