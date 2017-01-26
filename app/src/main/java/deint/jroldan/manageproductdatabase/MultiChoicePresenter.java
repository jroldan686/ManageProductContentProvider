package deint.jroldan.manageproductdatabase;

import android.util.SparseBooleanArray;

public class MultiChoicePresenter {
    SparseBooleanArray sparseBoolean;

    boolean isPositionChecked (int position) {
        Boolean result=sparseBoolean.get(position);
        return result==null?false:result;
    }
}
