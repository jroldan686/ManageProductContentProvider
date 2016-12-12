package deint.jroldan.manageproductfragment.presenter;

import android.content.Context;
import android.text.TextUtils;

import deint.jroldan.manageproductfragment.interfaces.LoginPresenter;
import deint.jroldan.manageproductfragment.model.Error;
import deint.jroldan.manageproductfragment.R;
import utils.ErrorMapUtils;

/**
 * Created by usuario on 6/10/16.
 */

public class LoginPresenterImpl implements LoginPresenter.Presenter {

    private LoginPresenter.View view;
    private int validateUser;
    private int validatePassword;
    private Context context;

    public LoginPresenterImpl(LoginPresenter.View loginView) {
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
