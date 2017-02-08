package deint.jroldan.manageproductcontentprovider.database;

import android.provider.BaseColumns;

import deint.jroldan.manageproductcontentprovider.provider.ManageProductContract;

public class DatabaseContract {

    private DatabaseContract() {}

    public static class Category implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME = "name";
        public static final String DEFAULT_SORT = COLUMN_NAME;
        public static final String[] ALL_COLUMNS = new String[]{BaseColumns._ID, COLUMN_NAME};
        public static final String SQL_CREATE_ENTRIES = String.format(" CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT NOT NULL)",
                TABLE_NAME, BaseColumns._ID,
                COLUMN_NAME);
        public static final String SQL_DETELE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static class Product implements BaseColumns {
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_BRAND = "brand";
        public static final String COLUMN_DOSAGE = "dosage";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_STOCK = "stock";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_IDCATEGORY = "idCategory";
        public static final String PRODUCT_JOIN_CATEGORY = String.format("%s INNER JOIN %s ON %s=%s.%s",
                TABLE_NAME, ManageProductContract.CategoryEntry.TABLE_NAME, COLUMN_IDCATEGORY, ManageProductContract.CategoryEntry.COLUMN_NAME, BaseColumns._ID);
        public static final String[] COLUMNS_PRODUCT_JOIN_CATEGORY = {
                TABLE_NAME + "." + COLUMN_NAME, COLUMN_DESCRIPTION, ManageProductContract.CategoryEntry.TABLE_NAME + "." + ManageProductContract.CategoryEntry.COLUMN_NAME
        };
        public static final String[] ALL_COLUMNS = new String[]{BaseColumns._ID,COLUMN_NAME,COLUMN_DESCRIPTION,COLUMN_BRAND,COLUMN_DOSAGE,COLUMN_PRICE,COLUMN_STOCK,COLUMN_IMAGE,COLUMN_IDCATEGORY};
        public static final String REFERENCE_ID_CATEGORY = String.format("REFERENCES %s (%s) ON UPDATE CASCADE, ON DELETE RESTRICT", ManageProductContract.CategoryEntry.TABLE_NAME, BaseColumns._ID);
        public static final String SQL_CREATE_ENTRIES = String.format(" CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT NOT NULL,"+
                        "%s TEXT NOT NULL,"+
                        "%s TEXT NOT NULL,"+
                        "%s TEXT NOT NULL,"+
                        "%s REAL NOT NULL,"+
                        "%s INTEGER NOT NULL,"+
                        "%s TEXT NOT NULL,"+
                        "%s INTEGER NOT NULL %s)",
                TABLE_NAME, BaseColumns._ID,
                COLUMN_NAME,
                COLUMN_DESCRIPTION,
                COLUMN_BRAND,
                COLUMN_DOSAGE,
                COLUMN_PRICE,
                COLUMN_STOCK,
                COLUMN_IMAGE,
                COLUMN_IDCATEGORY,
                REFERENCE_ID_CATEGORY);
        public static final String SQL_DETELE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static class InvoiceLine implements BaseColumns {
        public static final String TABLE_NAME = "invoiceline";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_IDINVOICE = "idInvoice";
        public static final String COLUMN_IDPRODUCT = "idProduct";
        public static final String REFERENCE_ID_INVOICE = String.format("REFERENCES %s (%s) ON UPDATE CASCADE, ON DELETE RESTRICT", ManageProductContract.InvoiceLineEntry.TABLE_NAME, BaseColumns._ID);
        public static final String REFERENCE_ID_PRODUCT = String.format("REFERENCES %s (%s) ON UPDATE CASCADE, ON DELETE RESTRICT", ManageProductContract.InvoiceLineEntry.TABLE_NAME, BaseColumns._ID);
        public static final String SQL_CREATE_ENTRIES = String.format(" CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT NOT NULL)",
                TABLE_NAME, BaseColumns._ID,
                COLUMN_AMOUNT,
                COLUMN_PRICE,
                COLUMN_IDINVOICE,
                COLUMN_IDPRODUCT,
                REFERENCE_ID_INVOICE,
                REFERENCE_ID_PRODUCT);
        public static final String SQL_DETELE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static class Invoice implements BaseColumns {
        public static final String TABLE_NAME = "invoice";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_IDPHARMACY = "idPharmacy";
        public static final String REFERENCE_ID_PHARMACY = String.format("REFERENCES %s (%s) ON UPDATE CASCADE, ON DELETE RESTRICT", ManageProductContract.InvoiceEntry.TABLE_NAME, BaseColumns._ID);
        public static final String SQL_CREATE_ENTRIES = String.format(" CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT NOT NULL)",
                TABLE_NAME, BaseColumns._ID,
                COLUMN_DATE,
                COLUMN_IDPHARMACY,
                REFERENCE_ID_PHARMACY);
        public static final String SQL_DETELE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static class InvoiceStatus implements BaseColumns {
        public static final String TABLE_NAME = "invoicestatus";
        public static final String COLUMN_NAME = "name";
        public static final String SQL_CREATE_ENTRIES = String.format(" CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT NOT NULL)",
                TABLE_NAME, BaseColumns._ID,
                COLUMN_NAME);
        public static final String SQL_DETELE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static class Pharmacy implements BaseColumns {
        public static final String TABLE_NAME = "invoicestatus";
        public static final String COLUMN_CIF = "name";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_EMAIL = "email";
        public static final String SQL_CREATE_ENTRIES = String.format(" CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT NOT NULL)",
                TABLE_NAME, BaseColumns._ID,
                COLUMN_CIF,
                COLUMN_ADDRESS,
                COLUMN_PHONE,
                COLUMN_EMAIL);
        public static final String SQL_DETELE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }
}
