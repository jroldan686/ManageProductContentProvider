package deint.jroldan.manageproductcontentprovider.presenter;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;

import deint.jroldan.manageproductcontentprovider.R;
import deint.jroldan.manageproductcontentprovider.database.DatabaseContract;
import deint.jroldan.manageproductcontentprovider.database.DatabaseManager;
import deint.jroldan.manageproductcontentprovider.interfaces.ManagePresenter;
import deint.jroldan.manageproductcontentprovider.model.Product;
import deint.jroldan.manageproductcontentprovider.provider.ManageProductContract;

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

    @Override
    public void add(Object object) {
        ContentValues contentValues;
        try {
            if(object instanceof Category) {
                contentValues = getContentCategory((Category)object);
            } else if (object instanceof Product) {
                contentValues = getContentProduct((Product)object);
                context.getContentResolver().insert(ManageProductContract.ProductEntry.CONTENT_URI);
            }
        } catch (SQLiteException e) {
            view.showMessage(e.getMessage());
        }
    }

    private ContentValues getContentProduct(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_NAME, product.getmName());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_DESCRIPTION, product.getmDescription());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_BRAND, product.getmBrand());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_DOSAGE, product.getmDosage());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_PRICE, product.getmPrice());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_STOCK, product.getmStock());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_IMAGE, product.getmImage());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_IDCATEGORY, product.getmCategory());
        return contentValues;
    }
}