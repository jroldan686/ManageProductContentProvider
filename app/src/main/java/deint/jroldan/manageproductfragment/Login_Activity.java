package deint.jroldan.manageproductfragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import deint.jroldan.manageproductfragment.interfaces.LoginPresenter;
import deint.jroldan.manageproductfragment.presenter.LoginPresenterImpl;

public class Login_Activity extends AppCompatActivity implements LoginPresenter.View {

    private LoginPresenterImpl account;
    private EditText edtPassword;
    private EditText edtUser;
    private TextView txvForgot;
    private TextInputLayout tilUser;
    private TextInputLayout tilPassword;
    private Button btnLogin;
    private final String TAG="login";
    private ViewGroup layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        layout=(ViewGroup)findViewById(R.id.activity_login);
        account = new LoginPresenterImpl(this);
        edtUser = (EditText)findViewById(R.id.edtUser);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        tilUser = (TextInputLayout)findViewById(R.id.tilUser);
        tilPassword = (TextInputLayout)findViewById(R.id.tilPassword);
        txvForgot = (TextView)findViewById(R.id.txvForgot);
        txvForgot.setMovementMethod(LinkMovementMethod.getInstance());
        Typeface font = Typeface.createFromAsset(getAssets(), "CFHalloweenRegular.ttf");
        txvForgot.setTypeface(font);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account.validateCredentialsLogin(tilUser.getEditText().getText().toString(),
                        tilPassword.getEditText().getText().toString());
            }
        });

        /*
        // Checking the persistence of the Application object
        if(((ManageProductRecycler_Application)getApplicationContext()).getUser!=null)
            Log.d("TAG",((ManageProductRecycler_Application)getApplicationContext()).getUser().toString());
        */
    }

    @Override
    public void setMessageError(String nameResource, int idView) {
        // Resource whose name is nameResource has to be taken
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
        //Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startActivity() {
        Intent intent = new Intent(this, ListProduct_Fragment.class);
        startActivity(intent);
    }

    private void resetValues() {
        edtPassword.setText("");
        edtUser.setText("");
    }

    protected void onStop() {
        super.onStop();
        Log.d(TAG,"Activity finalizada");
    }
}
