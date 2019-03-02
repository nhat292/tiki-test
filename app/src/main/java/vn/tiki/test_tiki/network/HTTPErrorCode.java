package vn.tiki.test_tiki.network;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by nhat on 3/2/19
 */

@Retention(RetentionPolicy.SOURCE)
@IntDef({HTTPErrorCode.LOGOUT})
public @interface HTTPErrorCode {
    int LOGOUT = 403;
}