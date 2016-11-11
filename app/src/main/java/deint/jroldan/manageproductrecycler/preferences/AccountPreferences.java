package deint.jroldan.manageproductrecycler.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import deint.jroldan.manageproductrecycler.interfaces.IPreferences;

/**
 * Created by usuario on 10/11/16.
 */

public class AccountPreferences implements IPreferences {

    private static IPreferences accountPreferences;
    public static final String FILE = "";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    private Context context;

    private AccountPreferences() {

    }

    //Singletón de la clase
    public static IPreferences getInstance(Context context){
        if(accountPreferences==null) {
            accountPreferences = new AccountPreferences();
            context=context;
        }
        return accountPreferences;
    }

    public static Editor putUser(Context context) {
        getEditor().putString(USER,user).apply();
    }
}
