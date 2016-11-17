package deint.jroldan.manageproductrecycler.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import deint.jroldan.manageproductrecycler.ManageProductRecycler_Application;
import deint.jroldan.manageproductrecycler.Product_Activity;
import deint.jroldan.manageproductrecycler.R;
import deint.jroldan.manageproductrecycler.interfaces.IValidateAccount;
import deint.jroldan.manageproductrecycler.interfaces.IValidateAccount.Presenter;
import deint.jroldan.manageproductrecycler.model.Error;
import utils.ErrorMapUtils;

/**
 * Created by usuario on 6/10/16.
 */

public class LoginPresenter implements Presenter {

    private IValidateAccount.View view;
    private int validateUser;
    private int validatePassword;
    private Context context;

    public LoginPresenter(IValidateAccount.View loginView) {
        this.view = loginView;
        context=(Context)view;
    }

    public void validateCredentialsLogin(String user, String password) {
        validateUser = validateCredentialsUser(user);
        validatePassword = validateCredentialsPassword(password);

        if(validateUser== Error.OK) {
            if(validatePassword==Error.OK) {
                view.startActivity();
            } else {
                    String nameResource = ErrorMapUtils.getErrorMap(context).get(String.valueOf(validatePassword));
                    view.setMessageError(nameResource, R.id.tilPassword);
                }
        } else {
            String nameResource = ErrorMapUtils.getErrorMap(context).get(String.valueOf(validateUser));
            view.setMessageError(nameResource, R.id.tilUser);
        }
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
}
