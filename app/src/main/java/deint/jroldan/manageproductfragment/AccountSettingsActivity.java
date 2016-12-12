package deint.jroldan.manageproductfragment;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import deint.jroldan.manageproductfragment.R;

/**
 * Created by usuario on 2/11/16.
 */

public class AccountSettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_account);
    }
}
