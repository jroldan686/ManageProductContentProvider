package deint.jroldan.manageproductdatabase.cursor;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

import deint.jroldan.manageproductdatabase.database.DatabaseManager;

public class CategoryCursorLoader extends CursorLoader {

    public CategoryCursorLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        return DatabaseManager.getInstance().getAllCategory();
    }
}
