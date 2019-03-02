package vn.tiki.test_tiki.di.module;

import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import io.reactivex.annotations.NonNull;

/**
 * Created by nhat on 3/2/19
 */
@Module
public class ContextModule {

    private Context appContext;

    public ContextModule(@NonNull Context context) {
        appContext = context;
    }

    @Provides
    @Singleton
    Context providerContext() {
        return appContext;
    }
}
