package deint.jroldan.manageproductrecycler.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import deint.jroldan.manageproductrecycler.interfaces.IPreferences;

/**
 * Created by usuario on 10/11/16.
 */

public class AccountPreferences implements IPreferences {

    private static IPreferences accountPreferences;
    public static final String FILE = "";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";

    private SharedPreferences sharedPreferences;

    private AccountPreferences(Context c) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(c);
    }

    //Singletón de la clase
    public static IPreferences getInstance(Context c){
        if(accountPreferences==null) {
            accountPreferences = new AccountPreferences(c);
        }
        return accountPreferences;
    }

    public void putUser(String user) {
        getEditor().putString(USER, user).apply();
    }

    public void putPassword(String password) {
        getEditor().putString(PASSWORD, password).apply();
    }

    public void putEmail(String email) {
        getEditor().putString(EMAIL, email).apply();
    }

    private SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }
}
