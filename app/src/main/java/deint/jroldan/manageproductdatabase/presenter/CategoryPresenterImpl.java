package deint.jroldan.manageproductdatabase.presenter;

import android.database.Cursor;
import android.widget.CursorAdapter;

import deint.jroldan.manageproductdatabase.database.DatabaseManager;
import deint.jroldan.manageproductdatabase.interfaces.CategoryPresenter;

public class CategoryPresenterImpl implements CategoryPresenter {

    private CategoryPresenter.View view;

    @Override
    public void getAllCategory(CursorAdapter adapter) {
        Cursor cursor = DatabaseManager.getInstance().getAllCategory();
        adapter.swapCursor(cursor);
        //view.getAdapterView().getAdapter().swapCursor(cursor);
    }
}
