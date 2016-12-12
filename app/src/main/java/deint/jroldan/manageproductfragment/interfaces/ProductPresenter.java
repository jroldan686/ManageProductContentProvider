package deint.jroldan.manageproductfragment.interfaces;

import java.util.List;

import deint.jroldan.manageproductfragment.model.Product;

/**
 * Created by usuario on 9/12/16.
 */

public interface ProductPresenter {

    void loadProducts();
    Product getProducts(String id);
    void deleteProduct(Product product);
    void onDestroy();

    interface View {
        void showProducts(List<Product> products);

        void showEmptyText(boolean show);

        void showMessage(String message);
    }
}
