package vn.tiki.test_tiki.di.module;

import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import vn.tiki.test_tiki.network.Manager;

/**
 * Created by nhat on 3/2/19
 */
@Module
public class ManagerModule {

    @Provides
    @Singleton
    Manager provideManager(Context context) {
        return new Manager(context);
    }
}