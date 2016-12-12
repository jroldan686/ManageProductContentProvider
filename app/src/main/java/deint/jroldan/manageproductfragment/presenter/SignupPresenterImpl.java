package deint.jroldan.manageproductfragment.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

import deint.jroldan.manageproductfragment.interfaces.LoginPresenter;
import deint.jroldan.manageproductfragment.interfaces.SignupPresenter;
import deint.jroldan.manageproductfragment.model.Error;
import deint.jroldan.manageproductfragment.preferences.AccountPreferencesImpl;
import deint.jroldan.manageproductfragment.R;
import utils.ErrorMapUtils;

/**
 * Created by usuario on 16/11/16.
 */

public class SignupPresenterImpl implements SignupPresenter.Presenter, SignupPresenter.PresenterUser {

    private int validateUser, validatePassword, validateEmail;
    private LoginPresenter.View view;
    private Context context;

    public SignupPresenterImpl(LoginPresenter.View view) {
        this.view = view;
        this.context = (Context)view;
    }

    public void validateCredentials(String user, String password, String email) {
        validateUser = validateCredentialsUser(user);
        validatePassword = validateCredentialsPassword(password);
        validateEmail = validateCredentialsEmail(email);

        if(validateUser== Error.OK) {
            if(validatePassword==Error.OK) {
                if(validateEmail == Error.OK) {
                    savePreferences(user, password, email);
                    // Se puede utilizar la llamada al método StarActivity con un Intent [...]
                    // parámetro y no tener que implementar el método startActivity en la [...]
                    // porque llama al método super.startActivity()
                    view.startActivity();
                }
            } else {
                String nameResource = ErrorMapUtils.getErrorMap(context).get(String.valueOf(validatePassword));
                view.setMessageError(nameResource, R.id.tilPassword);
            }
        } else {
            String nameResource = ErrorMapUtils.getErrorMap(context).get(String.valueOf(validateUser));
            view.setMessageError(nameResource, R.id.tilUser);
        }
    }

    private void savePreferences(String user, String password, String email) {
        AccountPreferencesImpl accountPreferences = (AccountPreferencesImpl) AccountPreferencesImpl.getInstance(context);
        accountPreferences.putUser(user);
        accountPreferences.putPassword(password);
        accountPreferences.putEmail(email);
    }

    @Override
    public int validateCredentialsUser(String user) {
        if (TextUtils.isEmpty(user)) {
            return Error.DATA_EMPTY;
        }
        return Error.OK;
    }

    @Override
    public int validateCredentialsPassword(String password) {
        int result = Error.OK;
        if(TextUtils.isEmpty(password)) {
            result = Error.DATA_EMPTY;
        } else {
            if (password.length() >= 8) {
                if (!password.matches("(.*)[0-9]+?(.*)")) {
                    result = Error.PASSWORD_DIGIT;
                }
                if (!(password.matches("(.*)[a-z]+?(.*)") && password.matches("(.*)[A-Z]+?(.*)"))) {
                    result = Error.PASSWORD_CASE;
                }
            } else {
                result = Error.PASSWORD_LENGTH;
            }
        }
        return result;
    }

    @Override
    public int validateCredentialsEmail(String email) {
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return Error.EMAIL_INVALID;
        else
            return Error.OK;
    }
}
