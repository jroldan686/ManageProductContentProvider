package deint.jroldan.manageproductrecycler.presenter;

import android.content.Context;
import android.content.Intent;

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
        validateUser = Presenter.validateCredentialsUser(user);
        validatePassword = Presenter.validateCredentialsPassword(password);

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
}
