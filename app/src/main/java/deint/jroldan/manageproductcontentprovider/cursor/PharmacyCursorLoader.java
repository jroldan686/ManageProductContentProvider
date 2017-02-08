package deint.jroldan.manageproductcontentprovider.cursor;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

import deint.jroldan.manageproductcontentprovider.database.DatabaseManager;

public class PharmacyCursorLoader extends CursorLoader {

    public PharmacyCursorLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        return DatabaseManager.getInstance().getAllPharmacy();
    }
}
