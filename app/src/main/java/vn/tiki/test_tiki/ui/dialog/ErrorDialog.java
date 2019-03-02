package vn.tiki.test_tiki.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.tiki.test_tiki.R;
import vn.tiki.test_tiki.utils.ScreenUtils;

/**
 * Created by nhat on 3/2/19
 */
public final class ErrorDialog extends Dialog {

    @BindView(R.id.txt_message)
    TextView mTxtMessage;
    @BindView(R.id.txt_title)
    TextView mTxtTitle;

    private Activity mOwnerActivity;
    private OnDialogClickListener mClickListener;

    public ErrorDialog(Context context) {
        super(context, R.style.ThemeDialog);
        mOwnerActivity = (context instanceof Activity) ? (Activity) context : null;
        if (mOwnerActivity != null)
            setOwnerActivity(mOwnerActivity);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mOwnerActivity = getOwnerActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_error);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.windowAnimations = R.style.ThemeAnimDialogFade;
            params.width = ScreenUtils.getScreenWidth(mOwnerActivity) * 8 / 10;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);
        }
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_ok)
    public void onOkClick(View v) {
        if (mClickListener != null) {
            mClickListener.ok(this);
        }
        dismiss();
    }

    @OnClick(R.id.btn_cancel)
    public void onCancelClick(View v) {
        if (mClickListener != null) {
            mClickListener.cancel();
        }
        dismiss();
    }

    public void show(String title, String message, OnDialogClickListener clickListener) {
        super.show();
        mTxtTitle.setText(title);
        mTxtMessage.setText(message);
        mClickListener = clickListener;
    }

}
