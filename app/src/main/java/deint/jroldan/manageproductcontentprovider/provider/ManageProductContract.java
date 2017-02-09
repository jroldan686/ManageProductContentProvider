package deint.jroldan.manageproductcontentprovider.provider;

import android.net.Uri;
import android.provider.BaseColumns;

import java.util.HashMap;

import deint.jroldan.manageproductcontentprovider.database.DatabaseContract;

import static deint.jroldan.manageproductcontentprovider.provider.ManageProductContract.CategoryEntry.CONTENT_PATH;

/**
 * Class which stores the database schema
 */
public class ManageProductContract {

    public static final String AUTHORITY = "deint.jroldan.manageproductcontentprovider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    private ManageProductContract() {

    }

    public static class CategoryEntry implements BaseColumns {
        public static final String CONTENT_PATH = "category";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String COLUMN_NAME = "name";
        public static final String[] ALL_COLUMNS = new String[]{BaseColumns._ID, COLUMN_NAME};
        public static final String IDCATEGORY = "idCategory";
    }

    public static class ProductEntry implements BaseColumns {
        public static final String CONTENT_PATH = "product";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_BRAND = "brand";
        public static final String COLUMN_DOSAGE = "dosage";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_STOCK = "stock";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_IDCATEGORY = "idCategory";
        public static final String[] ALL_COLUMNS = new String[]{BaseColumns._ID,COLUMN_NAME,COLUMN_DESCRIPTION,COLUMN_BRAND,COLUMN_DOSAGE,COLUMN_PRICE,COLUMN_STOCK,COLUMN_IMAGE,COLUMN_IDCATEGORY};

        public static final HashMap<String, String> sProductProjectionMapM;
        static {
            sProductProjectionMapM = new HashMap<>();
            sProductProjectionMapM.put(ManageProductContract.ProductEntry.COLUMN_NAME, DatabaseContract.Product.COLUMN_NAME);
            sProductProjectionMapM.put(ProductEntry.COLUMN_IDCATEGORY, DatabaseContract.Product.COLUMN_IDCATEGORY);
        }
    }

    public static class InvoiceLineEntry implements BaseColumns {
        public static final String CONTENT_PATH = "invoiceline";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_IDINVOICE = "idInvoice";
        public static final String COLUMN_IDPRODUCT = "idProduct";
    }

    public static class InvoiceEntry implements BaseColumns {
        public static final String CONTENT_PATH = "invoice";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_IDPHARMACY = "idPharmacy";
    }

    public static class InvoiceStatusEntry implements BaseColumns {
        public static final String CONTENT_PATH = "invoicestatus";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String COLUMN_NAME = "name";
    }

    public static class PharmacyEntry implements BaseColumns {
        public static final String CONTENT_PATH = "pharmacy";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String COLUMN_CIF = "name";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_EMAIL = "email";
    }
}
