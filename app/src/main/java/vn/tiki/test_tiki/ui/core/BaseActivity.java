package vn.tiki.test_tiki.ui.core;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vn.tiki.test_tiki.R;
import vn.tiki.test_tiki.ui.dialog.ErrorDialog;
import vn.tiki.test_tiki.ui.dialog.OnDialogClickListener;

/**
 * Created by nhat on 3/2/19
 */
public abstract class BaseActivity<T extends IBasePresenter> extends AppCompatActivity implements IBaseView<T> {

    protected static String TAG = BaseActivity.class.getSimpleName();

    private ProgressDialog mProgressDlg;
    private ErrorDialog errorDialog;

    private Unbinder unbinder;
    protected T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
    }

    public void setUnbinder() {
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    // ########## START PROGRESS DIALOG ##########
    protected void showProgressDialog() {
        if (isFinishing())
            return;
        if (mProgressDlg == null) {
            mProgressDlg = new ProgressDialog(this, R.style.ProgressDialogTheme);
        }
        try {
            mProgressDlg.setCancelable(false);
            mProgressDlg.show();
            mProgressDlg.setContentView(R.layout.progress_custom);
            mProgressDlg.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissProgressDialog() {
        if (mProgressDlg != null && mProgressDlg.isShowing() && !isFinishing()) {
            try {
                mProgressDlg.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // ########## END PROGRESS DIALOG ##########

    // ########## START MESSAGE DIALOG ##########
    protected void showMessageDialog(String title, String message, OnDialogClickListener clickListener) {
        try {
            if (mProgressDlg != null && mProgressDlg.isShowing())
                mProgressDlg.dismiss();
            if (errorDialog == null)
                errorDialog = new ErrorDialog(this);
            if (!errorDialog.isShowing())
                errorDialog.dismiss();
            errorDialog.show(title, message, clickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMessageDialog(String title, String message) {
        try {
            if (mProgressDlg != null && mProgressDlg.isShowing())
                mProgressDlg.dismiss();
            if (errorDialog == null)
                errorDialog = new ErrorDialog(this);
            if (!errorDialog.isShowing())
                errorDialog.show(title, message, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ########## END MESSAGE DIALOG ##########
}