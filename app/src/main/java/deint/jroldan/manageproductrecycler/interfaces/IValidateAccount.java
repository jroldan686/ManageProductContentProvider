package deint.jroldan.manageproductrecycler.interfaces;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import deint.jroldan.manageproductrecycler.R;

import deint.jroldan.manageproductrecycler.model.Error;

/**
 * Created by usuario on 6/10/16.
 */
public interface IValidateAccount {

    interface View{
        void setMessageError(String messageError, int idView);
        void startActivity();
    }

    interface Presenter{
        static int validateCredentialsUser(String user) {
            if (TextUtils.isEmpty(user)) {
                return Error.DATA_EMPTY;
            }
            return Error.OK;
        }
        static int validateCredentialsPassword(String password) {
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
                /*
                // We save the user in the Application class
                ((ManageProductRecycler_Application) ((Context) view).getApplicationContext()).setUser(new User(user, password));
                */
                } else {
                    result = Error.PASSWORD_LENGTH;
                }
            }
            return result;
        }
    }
}
