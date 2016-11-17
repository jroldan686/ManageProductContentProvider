package deint.jroldan.manageproductrecycler.interfaces;

import android.util.Patterns;

import deint.jroldan.manageproductrecycler.model.Error;

/**
 * Created by usuario on 11/11/16.
 */

public interface IValidateUser extends IValidateAccount {
    int EMAIL_INVALID = 14;

    interface PresenterUser {
        static int validateCredentialsEmail(String email) {
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                return Error.EMAIL_INVALID;
            else
                return Error.OK;
        }
    }
}
