package deint.jroldan.manageproductcontentprovider;

import android.app.Application;

import deint.jroldan.manageproductcontentprovider.database.DatabaseHelper;

/**
 * Class which stores a User in the Application class
 */

public class ManageProduct_Application extends Application {

    //private DatabaseHelper databaseHelper;
    public static ManageProduct_Application manageProduct_application;

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.getInstance().open();
    }

    public static ManageProduct_Application getContext() {
        return manageProduct_application;
    }
}
