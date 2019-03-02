package vn.tiki.test_tiki.ui.dialog;

/**
 * Created by nhat on 3/2/19
 */
public interface OnDialogClickListener<T> {

    void ok(T t);

    void cancel();
}
