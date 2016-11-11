package deint.jroldan.manageproductrecycler;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

/**
 * Created by usuario on 9/11/16.
 */

public class SignupActivity extends AppCompatActivity {

    private Spinner spnProvince;
    private Spinner spnCity;
    private Button btnSinup;
    private RadioGroup typeClient;
    private TextInputLayout tilCompanyName;
    private AdapterView.OnItemSelectedListener spinnerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        spnProvince=(Spinner)findViewById(R.id.spnProvince);
        spnCity=(Spinner)findViewById(R.id.spnCity);

        typeClient=(RadioGroup)findViewById(R.id.rdgTypeClient);
        typeClient.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdbParticular:
                        showCompany(false);
                        break;
                    case R.id.rdbCompany:
                        showCompany(true);
                        break;
                }
            }
        });

        tilCompanyName = (TextInputLayout)findViewById(R.id.tilCompany);

        loadSpinnerProvince();

    }

    /**
     * Method which shows the company name field
     * @param show True to show it, false to hide it
     */
    private void showCompany(boolean show) {
        tilCompanyName.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void loadSpinnerProvince() {

        spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getId()) {
                    case R.id.spnProvince:
                        TypedArray provinces = getResources().obtainTypedArray(R.array.array_provincia_a_localidades);
                        CharSequence[] cities = provinces.getTextArray(position);
                        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, cities);
                        spnCity.setAdapter(adapter);
                        break;
                    case R.id.spnCity:

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        //Initialise the Province Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.provincias, R.layout.activity_signup);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnProvince.setAdapter(adapter);

    }

    private boolean validate(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void signup(View view) {

    }
}
