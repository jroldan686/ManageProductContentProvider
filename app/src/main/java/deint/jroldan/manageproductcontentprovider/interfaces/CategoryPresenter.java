package deint.jroldan.manageproductcontentprovider.interfaces;

import android.content.Context;
import android.database.Cursor;
import android.widget.CursorAdapter;

public interface CategoryPresenter {

    void getAllCategory(CursorAdapter adapter);

    interface View {
        void setCursorCategory(Cursor cursor);
        Context getContext();
        Cursor getCursorCategory();
    }
}
