package deint.jroldan.manageproductrecycler.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import deint.jroldan.manageproductrecycler.interfaces.IPreferences;

/**
 * Created by usuario on 10/11/16.
 */

public class GeneralPreferences implements IPreferences {
    private static IPreferences generalPreferences;
    private SharedPreferences sharedPreferences;

    public static IPreferences getInstance(Context context) {
        if(generalPreferences == null) {
            generalPreferences = new GeneralPreferences(context);
        }
        return generalPreferences;
    }

    private GeneralPreferences(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
}
