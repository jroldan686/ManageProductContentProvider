package deint.jroldan.manageproductcontentprovider.interfaces;

import android.content.Context;

import java.util.List;

import deint.jroldan.manageproductcontentprovider.model.Product;

public interface ProductPresenter {

    void addProducts(Product product);
    void loadProducts();
    Product getProducts(String id);
    void deleteProduct(Product product);
    void onDestroy();

    interface View {
        void showProducts(List<Product> products);

        void showEmptyText(boolean show);

        void showMessage(String message);

        void showMessageDelete(Product product);

        Context getContext();
    }
}
