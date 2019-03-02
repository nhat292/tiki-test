package vn.tiki.test_tiki.ui.activities.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import java.lang.ref.WeakReference;
import vn.tiki.test_tiki.R;
import vn.tiki.test_tiki.ui.activities.main.MainActivity;
import vn.tiki.test_tiki.utils.AppConstants;

/**
 * Created by nhat on 3/2/19
 */
public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new HandlePassSplashScreen(this), AppConstants.SPLASH_DURATION);
    }

    private static class HandlePassSplashScreen implements Runnable {
        private WeakReference<SplashActivity> activityRf;

        public HandlePassSplashScreen(SplashActivity activity) {
            activityRf = new WeakReference<>(activity);
        }

        @Override
        public void run() {
            SplashActivity activity = activityRf.get();
            if (activity == null)
                return;
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
    }
}
