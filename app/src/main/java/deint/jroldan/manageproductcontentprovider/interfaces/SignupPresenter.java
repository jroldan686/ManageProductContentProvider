package deint.jroldan.manageproductcontentprovider.interfaces;

/**
 * Created by usuario on 11/11/16.
 */

public interface SignupPresenter extends LoginPresenter {
    int EMAIL_INVALID = 14;

    interface PresenterUser {

        int validateCredentialsEmail(String email);

/*        static int validateCredentialsEmail(String email) {
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                return Error.EMAIL_INVALID;
            else
                return Error.OK;
        }*/
    }
}
