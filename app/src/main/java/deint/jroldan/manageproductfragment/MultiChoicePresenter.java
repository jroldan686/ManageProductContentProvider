package deint.jroldan.manageproductfragment;

import android.util.SparseBooleanArray;

/**
 * Created by usuario on 16/12/16.
 */

public class MultiChoicePresenter {
    SparseBooleanArray sparseBoolean;

    boolean isPositionChecked (int position) {
        Boolean result=sparseBoolean.get(position);
        return result==null?false:result;
    }
}
