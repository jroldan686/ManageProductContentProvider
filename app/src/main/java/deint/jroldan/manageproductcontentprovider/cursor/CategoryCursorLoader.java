package deint.jroldan.manageproductcontentprovider.cursor;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

import deint.jroldan.manageproductcontentprovider.database.DatabaseManager;

public class CategoryCursorLoader extends CursorLoader {

    public CategoryCursorLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        return DatabaseManager.getInstance().getAllCategory();
    }
}
