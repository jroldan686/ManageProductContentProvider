package deint.jroldan.manageproductrecycler.presenter;

import android.content.Context;
import android.content.Intent;

import deint.jroldan.manageproductrecycler.Product_Activity;
import deint.jroldan.manageproductrecycler.R;
import deint.jroldan.manageproductrecycler.interfaces.IValidateAccount;
import deint.jroldan.manageproductrecycler.interfaces.IValidateUser;
import deint.jroldan.manageproductrecycler.model.Error;
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
        validateUser = IValidateAccount.Presenter.validateCredentialsUser(user);
        validatePassword = IValidateAccount.Presenter.validateCredentialsPassword(password);
        validateEmail = IValidateUser.PresenterUser.validateCredentialsEmail(email);

        if(validateUser== Error.OK) {
            if(validatePassword==Error.OK) {
                if(validateEmail == Error.OK) {
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
}
