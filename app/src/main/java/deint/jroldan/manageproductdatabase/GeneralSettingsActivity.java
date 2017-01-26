package deint.jroldan.manageproductdatabase;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by usuario on 2/11/16.
 */

public class GeneralSettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_general);
    }
}
