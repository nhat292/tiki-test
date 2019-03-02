package vn.tiki.test_tiki.di.component;

import javax.inject.Singleton;

import dagger.Component;
import vn.tiki.test_tiki.ui.activities.main.MainActivity;
import vn.tiki.test_tiki.di.module.ContextModule;
import vn.tiki.test_tiki.di.module.ManagerModule;

/**
 * Created by nhat on 3/2/19
 */
@Singleton
@Component(modules = {ContextModule.class, ManagerModule.class})
public interface ApplicationComponent {
    void inject(MainActivity activity);
}
