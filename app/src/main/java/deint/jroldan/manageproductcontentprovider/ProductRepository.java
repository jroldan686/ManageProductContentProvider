package deint.jroldan.manageproductcontentprovider;

import java.util.List;

import deint.jroldan.manageproductcontentprovider.model.Product;

public class ProductRepository {
    private static ProductRepository repository;
    private List<Product> products;

    public ProductRepository(ProductRepository repository) {
        this.repository = repository;
    }

    public static ProductRepository getInstance() {
        if(repository == null)
            repository = new ProductRepository();
        return repository;
    }

    public List<Product> getProducts() {
        return null;
    }
}
