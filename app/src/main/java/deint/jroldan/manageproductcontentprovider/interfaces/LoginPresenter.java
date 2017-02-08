package deint.jroldan.manageproductcontentprovider.interfaces;

/**
 * Created by usuario on 6/10/16.
 */
public interface LoginPresenter {

    interface View{
        void setMessageError(String messageError, int idView);
        void startActivity();
    }

    interface Presenter{

        int validateCredentialsUser(String user);
        int validateCredentialsPassword(String password);

/*        static int validateCredentialsUser(String user) {
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
                *//*
                // We save the user in the Application class
                ((ManageProduct_Application) ((Context) view).getApplicationContext()).setUser(new User(user, password));
                *//*
                } else {
                    result = Error.PASSWORD_LENGTH;
                }
            }
            return result;
        }*/
    }
}
