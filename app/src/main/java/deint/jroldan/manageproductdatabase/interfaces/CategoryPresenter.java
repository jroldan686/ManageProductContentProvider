package deint.jroldan.manageproductdatabase.interfaces;

import android.content.Context;
import android.database.Cursor;
import android.widget.CursorAdapter;

import deint.jroldan.manageproductdatabase.cursor.CategoryCursorLoader;

public interface CategoryPresenter {

    void getAllCategory(CursorAdapter adapter);

    interface View {
        void setCursorCategory(Cursor cursor);
        Context getContext();
    }
}
