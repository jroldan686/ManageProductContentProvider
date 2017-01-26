package deint.jroldan.manageproductdatabase.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

import deint.jroldan.manageproductdatabase.ManageProduct_Application;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "ManageProduct.db";
    private volatile static DatabaseHelper databaseHelper;       // Singleton
    private AtomicInteger mOpenCounter;
    private SQLiteDatabase mDatabase;

    private DatabaseHelper(Context context) {
        super(ManageProduct_Application.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
        mOpenCounter = new AtomicInteger();
    }

    public synchronized static DatabaseHelper getInstance() {
        if(databaseHelper == null) {
            databaseHelper = new DatabaseHelper(ManageProduct_Application.getContext());
        }
        return databaseHelper;
    }

    public synchronized SQLiteDatabase openDatabase() {
        if(mOpenCounter.incrementAndGet()==1) {
            mDatabase = getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        if(mOpenCounter.decrementAndGet()==0) {
            mDatabase.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(ManageProductContract.CategoryEntry.SQL_CREATE_ENTRIES);
            db.execSQL(ManageProductContract.ProductEntry.SQL_CREATE_ENTRIES);
            db.execSQL(ManageProductContract.InvoiceLineEntry.SQL_CREATE_ENTRIES);
            db.execSQL(ManageProductContract.InvoiceEntry.SQL_CREATE_ENTRIES);
            db.execSQL(ManageProductContract.InvoiceStatusEntry.SQL_CREATE_ENTRIES);
            db.execSQL(ManageProductContract.PharmacyEntry.SQL_CREATE_ENTRIES);
            db.setTransactionSuccessful();
        } catch (SQLiteException ex) {
            Log.e("Manage","Error" + ex.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();
        try {
            db.execSQL(ManageProductContract.PharmacyEntry.SQL_DETELE_ENTRIES);
            db.execSQL(ManageProductContract.InvoiceStatusEntry.SQL_DETELE_ENTRIES);
            db.execSQL(ManageProductContract.InvoiceEntry.SQL_DETELE_ENTRIES);
            db.execSQL(ManageProductContract.InvoiceLineEntry.SQL_DETELE_ENTRIES);
            db.execSQL(ManageProductContract.ProductEntry.SQL_DETELE_ENTRIES);
            db.execSQL(ManageProductContract.CategoryEntry.SQL_DETELE_ENTRIES);
            onCreate(db);
            db.setTransactionSuccessful();
        } catch (SQLiteException ex) {
            Log.e("Manage","Error" + ex.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, newVersion, oldVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()) {
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN)
                db.setForeignKeyConstraintsEnabled(true);
            else
                db.execSQL("PRAGMA foreign_keys=ON");
        }
    }

    public SQLiteDatabase open() {
        return getWritableDatabase();
    }
}
