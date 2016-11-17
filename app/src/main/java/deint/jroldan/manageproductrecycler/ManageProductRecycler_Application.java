package deint.jroldan.manageproductrecycler;

import android.app.Application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import deint.jroldan.manageproductrecycler.model.Product;

/**
 * Created by usuario on 6/10/16.
 */

/**
 * Class which stores a User in the Application class
 */

public class ManageProductRecycler_Application extends Application {
    /*
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    */

    private ArrayList<Product> products = new ArrayList<Product>();

    @Override
    public void onCreate() {
        super.onCreate();
        ProductRepository();
    }

    private void ProductRepository() {
        saveProduct(new Product("Ibuprofeno","Ibuprofeno 600 mg", "Cinfa", "600 mg", 9.99, 5, R.drawable.ibuprofeno));
        saveProduct(new Product("Aspirina Complex", "Granulado efervescente", "Bayer", "250 mg", 6.00, 3, R.drawable.aspirina_complex));
        saveProduct(new Product("Pulmofasa", "Jarabe", "Baster", "180ml", 7.95, 6, R.drawable.pulmofasa_jarabe_180_ml));
        saveProduct(new Product("Frenadol", "Sobres", "Frenadol", "10 sobres", 8.00, 4, R.drawable.frenadol_complex_sobres));
    }

    private void saveProduct(Product product) {
        if(!this.products.contains(product))
            this.products.add(product);
    }

    public List<Product> getProducts() {
        //Collections.sort(products, Product.PRICE_COMPARATOR);

        // Lambda expression
        //Collections.sort(products, (p1, p2) -> Double.compare(p1.getmPrice(), p2.getmPrice()));
        return products;
    }

    public void addProduct(Product p) { saveProduct(p); }
}
