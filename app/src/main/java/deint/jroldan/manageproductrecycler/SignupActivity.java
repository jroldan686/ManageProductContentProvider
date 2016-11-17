package deint.jroldan.manageproductrecycler;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import deint.jroldan.manageproductrecycler.interfaces.IValidateUser;
import deint.jroldan.manageproductrecycler.model.User;
import deint.jroldan.manageproductrecycler.presenter.SignupPresenter;

/**
 * Created by usuario on 9/11/16.
 */

public class SignupActivity extends AppCompatActivity implements IValidateUser.View {

    private Spinner spnProvince;
    private Spinner spnCity;
    private Button btnSinup;
    private RadioGroup typeClient;
    private TextInputLayout tilCompanyName;
    private AdapterView.OnItemSelectedListener spinnerListener;
    private SignupPresenter presenter;
    private ViewGroup layout;
    EditText edtUser, edtPassword, edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        layout=(ViewGroup)findViewById(R.id.activity_login);
        spnProvince=(Spinner)findViewById(R.id.spnProvince);
        spnCity=(Spinner)findViewById(R.id.spnCity);

        presenter = new SignupPresenter(this);

        edtUser = (EditText)findViewById(R.id.edtUserName);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        edtEmail = (EditText)findViewById(R.id.edtEmail);

        typeClient=(RadioGroup)findViewById(R.id.rdgTypeClient);
        initUserType();

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

    private void showCitySelected() {
        String mensaje = String.format(getString(R.string.message_provinces_city),
                spnProvince.getSelectedItem().toString(),
                spnCity.getSelectedItem().toString());
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    private void loadSpinnerProvince() {

        spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getId()) {
                    case R.id.spnProvince:
                        TypedArray provinces = getResources().obtainTypedArray(R.array.array_provincia_a_localidades);
                        CharSequence[] cities = provinces.getTextArray(position);
                        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(SignupActivity.this, android.R.layout.simple_spinner_item, cities);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnCity.setAdapter(adapter);
                        break;
                    case R.id.spnCity:
                        showCitySelected();
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
        presenter.validateCredentials(edtUser.getText().toString(), edtPassword.getText().toString(), edtEmail.getText().toString());
    }

    private void initUserType() {
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
    }

    /**
     * Method which shows the error in a personalised message using a {@link Snackbar}
     * @param nameResource Error String name to be shown, using {@link #getResources().getIdentifier()} to obtain the id from R class
     * @link {android.content.res.Resources#getIdentifier(String, String, String getIdentifier()} to obtain the id from R class
     * @param idView the id of view where show the error
     */
    @Override
    public void setMessageError(String nameResource, int idView) {
        String messageError = getResources().getString(getResources().getIdentifier(nameResource,"string",getPackageName()));
        switch (idView) {
            case R.id.tilPassword:
                //tilPassword.setError(messageError);
                Snackbar.make(layout, messageError,Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.tilUser:
                //tilUser.setError(messageError);
                Snackbar.make(layout, messageError,Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void startActivity() {
        Intent intent = new Intent(this, Product_Activity.class);
        startActivity(intent);
        finish();
    }
}
