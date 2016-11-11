package deint.jroldan.manageproductrecycler;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import deint.jroldan.manageproductrecycler.interfaces.ILoginMvp;
import deint.jroldan.manageproductrecycler.presenter.LoginPresenter;

public class Login_Activity extends AppCompatActivity implements ILoginMvp.View {

    private ILoginMvp.Presenter loginMvp;
    private EditText edtPassword;
    private EditText edtUser;
    private TextView txvForgot;
    private TextInputLayout tilUser;
    private TextInputLayout tilPassword;
    private LoginPresenter loginPresenter;
    private Button btnLogin;
    private final String TAG="login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginMvp = new LoginPresenter(this);
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
                loginMvp.validateCredentials(tilUser.getEditText().getText().toString(), tilPassword.getEditText().getText().toString());
            }
        });

        /*
        // Checking the persistence of the Application object
        if(((ManageProductRecycler_Application)getApplicationContext()).getUser!=null)
            Log.d("TAG",((ManageProductRecycler_Application)getApplicationContext()).getUser().toString());
        */
    }

    @Override
    public void setMessageError(String messageError, int idView) {
        switch (idView) {
            case R.id.edtPassword:
                edtPassword.setError(messageError);
                break;
            case R.id.edtUser:
                edtUser.setError(messageError);
        }
        //Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void launchActivity() {
        Intent intent = new Intent(this, Product_Activity.class);
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
