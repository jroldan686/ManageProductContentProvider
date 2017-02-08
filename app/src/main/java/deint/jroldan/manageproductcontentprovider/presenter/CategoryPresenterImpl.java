package deint.jroldan.manageproductcontentprovider.presenter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;

import deint.jroldan.manageproductcontentprovider.interfaces.CategoryPresenter;
import deint.jroldan.manageproductcontentprovider.provider.ManageProductContract;

public class CategoryPresenterImpl implements CategoryPresenter, LoaderManager.LoaderCallbacks<Cursor> {

    private CategoryPresenter.View view;
    private Context context;
    private final static int CATEGORY = 1;

    public CategoryPresenterImpl(CategoryPresenter.View view) {
        this.view = view;
        this.context = view.getContext();
    }

    @Override
    public void getAllCategory(CursorAdapter adapter) {
        //Cursor cursor = DatabaseManager.getInstance().getAllCategory();
        //adapter.swapCursor(cursor);
        //view.getAdapterView().getAdapter().swapCursor(cursor);

        ((Activity)context).getLoaderManager().initLoader(CATEGORY,null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Loader<Cursor> loader = null;
        switch (id)
        {
            case CATEGORY:
                loader = new CursorLoader(context, ManageProductContract.CategoryEntry.CONTENT_URI, ManageProductContract.CategoryEntry.ALL_COLUMNS, null, null, ManageProductContract.CategoryEntry.DEFAULT_SORT);
                break;
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        view.setCursorCategory(cursor);
        view.getCursorCategory().setNotificationUri(context.getContentResolver(), ManageProductContract.CategoryEntry.CONTENT_URI);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.setCursorCategory(null);
    }


}
