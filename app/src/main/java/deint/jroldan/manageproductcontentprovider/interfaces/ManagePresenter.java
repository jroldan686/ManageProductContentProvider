package deint.jroldan.manageproductcontentprovider.interfaces;

import deint.jroldan.manageproductcontentprovider.model.Product;

/**
 * Interface which implements methods to be implemented by ManageProductsFragment
 */
public interface ManagePresenter {
    void saveProduct(Product product);
    void updateProduct(Product oldOne, Product newOne);
    void onDestroy();

    interface View {
        void showMessage(String message);
    }
}
