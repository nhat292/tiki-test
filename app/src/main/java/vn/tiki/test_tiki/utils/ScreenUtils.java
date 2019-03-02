package vn.tiki.test_tiki.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by nhat on 3/2/19
 */
public final class ScreenUtils {

    private ScreenUtils() {

    }

    private static int screenWidth;
    private static int screenHeight;

    /**
     * Get screen size
     *
     * @param mContext
     */
    private static void getScreenSize(Context mContext) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
    }

    /**
     * Get screen width size
     *
     * @param mContext
     * @return
     */
    public static int getScreenWidth(Context mContext) {
        if (screenWidth == 0)
            getScreenSize(mContext);
        return screenWidth;
    }

    /**
     * Get screen height size
     *
     * @param mContext
     * @return
     */
    public static int getScreenHeight(Context mContext) {
        if (screenHeight == 0) {
            getScreenSize(mContext);
        }

        return screenHeight;
    }
}
