package deint.jroldan.manageproductfragment.presenter;

import android.content.Context;

import deint.jroldan.manageproductfragment.ProductRepository;
import deint.jroldan.manageproductfragment.interfaces.ProductPresenter;
import deint.jroldan.manageproductfragment.model.Product;

/**
 * Created by usuario on 9/12/16.
 */

public class ProductPresenterImpl implements ProductPresenter {

    private ProductPresenter.View view;
    private ProductRepository repository;

    public ProductPresenterImpl(ProductPresenter.View view) {
        this.view = view;
        this.repository = ProductRepository.getInstance();
    }

    @Override
    public void loadProducts() {
        if(repository.getProducts().isEmpty())
            view.showEmptyText(true);
        else
            view.showProducts(repository.getProducts());
    }

    @Override
    public Product getProducts(String id) {
        //return repository.getProduct(id);
        return null;
    }

    @Override
    public void deleteProduct(Product product) {
        repository.deleteProduct(product);

        // DEPENDS OF THE IMPLEMENTATION
        //loadProducts();
        view.getAdapter().deleteProduct();
        if(view.getAdapter().isEmpty()) {
            view.showEmptyText(true);
        }
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
