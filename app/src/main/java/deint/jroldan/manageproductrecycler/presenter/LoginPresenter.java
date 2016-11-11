package deint.jroldan.manageproductrecycler.presenter;

import android.content.Context;
import android.text.TextUtils;

import deint.jroldan.manageproductrecycler.R;
import deint.jroldan.manageproductrecycler.interfaces.ILoginMvp;

/**
 * Created by usuario on 6/10/16.
 */

public class LoginPresenter implements ILoginMvp.Presenter {

    private ILoginMvp.View view;

    public LoginPresenter(ILoginMvp.View view) {
        this.view = view;
    }

    @Override
    public void validateCredentials(String user, String password) {
        if(TextUtils.isEmpty(user)) {
            view.setMessageError(((Context)view).getResources().getString(R.string.data_empty), R.id.edtUser);
        }
        else if(TextUtils.isEmpty(password)) {
            view.setMessageError(((Context)view).getResources().getString(R.string.data_empty), R.id.edtPassword);
        } else {
            view.setMessageError(((Context) view).getResources().getString(R.string.data_empty), R.id.edtPassword);
            if (password.length() >= 8) {
                if (!password.matches("(.*)[0-9]+?(.*)")) {
                    view.setMessageError(((Context) view).getResources().getString(R.string.password_digit), R.id.edtPassword);
                }
                if (!(password.matches("(.*)[a-z]+?(.*)") && password.matches("(.*)[A-Z]+?(.*)"))) {
                    view.setMessageError(((Context) view).getResources().getString(R.string.password_case), R.id.edtPassword);
                }
                /*
                // We save the user in the Application class
                ((ManageProductRecycler_Application) ((Context) view).getApplicationContext()).setUser(new User(user, password));
                */
            } else {
                view.setMessageError(((Context) view).getResources().getString(R.string.password_length), R.id.edtPassword);
            }
        }
    }
}
