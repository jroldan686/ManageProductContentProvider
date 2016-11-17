package deint.jroldan.manageproductrecycler.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;

import deint.jroldan.manageproductrecycler.Product_Activity;
import deint.jroldan.manageproductrecycler.R;
import deint.jroldan.manageproductrecycler.interfaces.IValidateAccount;
import deint.jroldan.manageproductrecycler.interfaces.IValidateUser;
import deint.jroldan.manageproductrecycler.model.Error;
import deint.jroldan.manageproductrecycler.preferences.AccountPreferences;
import utils.ErrorMapUtils;

/**
 * Created by usuario on 16/11/16.
 */

public class SignupPresenter implements IValidateUser.Presenter, IValidateUser.PresenterUser {

    private int validateUser, validatePassword, validateEmail;
    private IValidateAccount.View view;
    private Context context;

    public SignupPresenter(IValidateAccount.View view) {
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
        AccountPreferences accountPreferences = (AccountPreferences)AccountPreferences.getInstance(context);
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
