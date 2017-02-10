package deint.jroldan.manageproductcontentprovider.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import deint.jroldan.manageproductcontentprovider.database.DatabaseContract;
import deint.jroldan.manageproductcontentprovider.database.DatabaseHelper;

public class ManageProductProvider extends ContentProvider {
    public static final int PRODUCT = 1;
    public static final int PRODUCT_ID = 2;
    public static final int CATEGORY = 3;
    public static final int CATEGORY_ID = 4;
    public static final int INVOICESTATUS = 5;
    public static final int INVOICESTATUS_ID = 6;
    public static final int PHARMACY = 7;
    public static final int PHARMACY_ID = 8;
    public static final int INVOICELINE = 9;
    public static final int INVOICELINE_ID = 10;
    public static final int INVOICE = 11;
    public static final int INVOICE_ID = 12;

    private SQLiteDatabase sqLiteDatabase;

    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.ProductEntry.CONTENT_PATH, PRODUCT);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.ProductEntry.CONTENT_PATH + "/#", PRODUCT_ID);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.CategoryEntry.CONTENT_PATH, CATEGORY);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.CategoryEntry.CONTENT_PATH + "/#", CATEGORY_ID);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceStatusEntry.CONTENT_PATH, INVOICESTATUS);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceStatusEntry.CONTENT_PATH + "/#", INVOICESTATUS_ID);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.PharmacyEntry.CONTENT_PATH, PHARMACY);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.PharmacyEntry.CONTENT_PATH + "/#", PHARMACY_ID);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceLineEntry.CONTENT_PATH, INVOICELINE);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceLineEntry.CONTENT_PATH + "/#", INVOICELINE_ID);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceEntry.CONTENT_PATH, INVOICE);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceEntry.CONTENT_PATH + "/#", INVOICE_ID);
    }


    @Override
    public boolean onCreate() {
        sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        switch (uriMatcher.match(uri)) {
            case CATEGORY:
                sqLiteQueryBuilder.setTables(DatabaseContract.Category.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder))
                    sortOrder = DatabaseContract.Category.DEFAULT_SORT;
                break;
            case CATEGORY_ID:

                break;
            case PRODUCT:
                sqLiteQueryBuilder.setTables(DatabaseContract.Product.TABLE_NAME);
                sqLiteQueryBuilder.setProjectionMap(ManageProductContract.ProductEntry.sProductProjectionMapM);
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = DatabaseContract.Product.DEFAULT_SORT;
                }
                break;
            case PRODUCT_ID:
                break;
            case UriMatcher.NO_MATCH:
                throw new IllegalArgumentException("URI invalid");
        }
        String sqlQuery = sqLiteQueryBuilder.buildQuery(projection,selection,null,null,sortOrder,null);
        Log.i("manageproductcontent",sqlQuery);
        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase,projection,selection,selectionArgs,null,null,sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri newUri;
        long regId;
        switch (uriMatcher.match(uri)) {
            case CATEGORY:
                regId = sqLiteDatabase.insert(DatabaseContract.Category.TABLE_NAME, null, values);
                newUri = ContentUris.withAppendedId(uri,regId);
                break;

            case PRODUCT:
                regId = sqLiteDatabase.insert(DatabaseContract.Product.TABLE_NAME, null, values);
                newUri = ContentUris.withAppendedId(uri,regId);
                break;
        }
        if(regId!=-1) {
            // Notify the observers that an URI was modified
            getContext().getContentResolver().notifyChange(newUri,null);
        }
        else
        throw new SQLException()
        return newUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int affected = -1;
        switch (uriMatcher.match(uri)) {
            case CATEGORY:
                affected=sqLiteDatabase.update(DatabaseContract.CategoryEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case CATEGORY_ID:
                //rowid=uri.getLastPathSegment();
                //content://com.example.manageproductcontent/categoria/1
                rowid=uri.getPathSegments().get(1);
                selection=DatabaseContract.CategoryEntry._ID+"=?";
                selectionArgs= new String[]{rowid};
                affected=sqLiteDatabase.update(DatabaseContract.CategoryEntry.TABLE_NAME, values, selection, selectionArgs);

                break;
            case PRODUCT_ID:
                break;
            case PRODUCT:
                affected=sqLiteDatabase.update(DatabaseContract.ProductEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
        }
    }
}
