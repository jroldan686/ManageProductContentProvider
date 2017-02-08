package deint.jroldan.manageproductcontentprovider.dialog;

import android.app.DialogFragment;

import deint.jroldan.manageproductcontentprovider.model.Product;

/**
 * Created by usuario on 9/12/16.
 */

public class ConfirmDialog extends DialogFragment {
    private OnDeteleProductListener deteleProductListener;

    public interface OnDeleteProductListener {
        void deleteProduct(Product product);
    }

}
