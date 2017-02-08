package deint.jroldan.manageproductcontentprovider.presenter;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.util.List;

import deint.jroldan.manageproductcontentprovider.ProductRepository;
import deint.jroldan.manageproductcontentprovider.database.DatabaseManager;
import deint.jroldan.manageproductcontentprovider.interfaces.ProductPresenter;
import deint.jroldan.manageproductcontentprovider.model.Product;

public class ProductPresenterImpl implements ProductPresenter {

    private ProductPresenter.View view;
    private ProductRepository repository;

    public ProductPresenterImpl(ProductPresenter.View view) {
        this.view = view;
        this.repository = ProductRepository.getInstance();
    }

    @Override
    public void addProducts(Product product) {

    }

    @Override
    public void loadProducts() {
        /*
        if(repository.getProducts().isEmpty())
            view.showEmptyText(true);
        else
            view.showProducts(repository.getProducts());
        */
        new AsyncTask<Void, Void, List<Product>>() {
            ProgressDialog progressDialog = new ProgressDialog(view.getContext());
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setMessage("loading...");
                progressDialog.show();
            }

            @Override
            protected List<Product> doInBackground(Void... params) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return DatabaseManager.getInstance().getAllProducts();
            }

            @Override
            protected void onPostExecute(List<Product> products) {
                super.onPostExecute(products);
                if(progressDialog!=null)
                    progressDialog.dismiss();
                view.showProducts(products);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }
        }.execute();
    }

    @Override
    public Product getProducts(String id) {
        //return repository.getProduct(id);
        return null;
    }

    @Override
    public void deleteProduct(Product product) {
        //repository.deleteProduct(product);

        // DEPENDS OF THE IMPLEMENTATION
        loadProducts();
        view.showMessageDelete(product);
        //view.getAdapter().deleteProduct();
        //if(view.getAdapter().isEmpty()) {
        //    view.showEmptyText(true);
        //}
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    /* Example implementation to delete product once the SnackBar times out
    @Override
    public void deleteFinallyProduct(Product product) {
        repository.deleteProduct(product);
        loadProducts();
    }*/

    public void addProduct(Product product) {
        DatabaseManager.getInstance().addProduct(product);
        loadProducts();
    }


}
