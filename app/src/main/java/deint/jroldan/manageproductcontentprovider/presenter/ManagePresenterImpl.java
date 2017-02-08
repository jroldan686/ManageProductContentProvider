package deint.jroldan.manageproductcontentprovider.presenter;

import deint.jroldan.manageproductcontentprovider.R;
import deint.jroldan.manageproductcontentprovider.database.DatabaseManager;
import deint.jroldan.manageproductcontentprovider.interfaces.ManagePresenter;
import deint.jroldan.manageproductcontentprovider.model.Product;

public class ManagePresenterImpl implements ManagePresenter{
    ManagePresenter.View view;

    public ManagePresenterImpl(ManagePresenter.View view) {
        this.view = view;
    }

    @Override
    public void saveProduct(Product product) {
        DatabaseManager repository = DatabaseManager.getInstance();
        if (repository.getAllProducts().contains(product))
            view.showMessage(Integer.toString(R.string.already_exists));
        else
            repository.addProduct(product);
    }

    @Override
    public void updateProduct(Product oldOne, Product newOne) {
        DatabaseManager.getInstance().deleteProduct(oldOne);
        DatabaseManager.getInstance().addProduct(newOne);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}