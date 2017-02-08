package deint.jroldan.manageproductcontentprovider;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import deint.jroldan.manageproductcontentprovider.provider.ManageProductContract;
import deint.jroldan.manageproductcontentprovider.interfaces.CategoryPresenter;
import deint.jroldan.manageproductcontentprovider.interfaces.ManagePresenter;
import deint.jroldan.manageproductcontentprovider.model.Product;

public class ManageProduct_Fragment extends Fragment implements ManagePresenter.View, CategoryPresenter.View {

    private EditText edtName, edtDescription, edtBrand, edtDosage, edtStock, edtPrice;
    private Button btnInsertImage, btnAddProduct;
    private boolean addAction;
    private SimpleCursorAdapter adapterCategory;
    private Spinner spCategory;
    private ManageProductListener manageProductListener;
    private ManagePresenter managePresenter;

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        android.view.View rootView = inflater.inflate(R.layout.activity_manage_product, container, false);

        edtName = (EditText)rootView.findViewById(R.id.edtName);
        edtDescription = (EditText)rootView.findViewById(R.id.edtDescription);
        edtBrand = (EditText)rootView.findViewById(R.id.edtBrand);
        edtDosage = (EditText)rootView.findViewById(R.id.edtDosage);
        edtStock = (EditText)rootView.findViewById(R.id.edtStock);
        edtPrice = (EditText)rootView.findViewById(R.id.edtPrice);
        btnInsertImage = (Button)rootView.findViewById(R.id.btnInsertImage);
        btnInsertImage.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

            }
        });
        btnAddProduct = (Button)rootView.findViewById(R.id.btnAddProduct);
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        spCategory = (Spinner)rootView.findViewById(R.id.spCategory);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] from = {ManageProductContract.CategoryEntry.COLUMN_NAME};
        int[] to = {android.R.id.text1};
        adapterCategory = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_spinner_item, null, from, to, 0);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapterCategory);
    }

    @Override
    private void saveProduct() {
        Product productNew;
        Cursor cursor = ((SimpleCursorAdapter)spCategory.getAdapter()).getCursor();
        cursor.moveToPosition(spCategory.getSelectedItemPosition());

        if(addAction) {
            productNew = new Product(edtName.getText().toString(),
                    edtDescription.getText().toString(),
                    edtBrand.getText().toString(),
                    edtDosage.getText().toString(),
                    Double.valueOf(edtPrice.getText().toString()),
                    Integer.valueOf(edtStock.getText().toString()),
                    R.drawable.aspirina_complex,
                    cursor.getInt(0));
            managePresenter.addProduct(productNew);
        } else {
            productNew = new Product(edtName.getText().toString(),
                    edtDescription.getText().toString(),
                    edtBrand.getText().toString(),
                    edtDosage.getText().toString(),
                    Double.valueOf(edtPrice.getText().toString()),
                    Integer.valueOf(edtStock.getText().toString()),
                    R.drawable.aspirina_complex,
                    cursor.getInt(0));
            managePresenter.updateProduct(productNew);
        }
        mCalback.onListProductListener();

        /*
        String name = edtName.getText().toString();
        String description = edtDescription.getText().toString();
        String brand = edtBrand.getText().toString();
        String dosage = edtDosage.getText().toString();
        int stock = Integer.valueOf(edtStock.getText().toString());
        double price = Double.valueOf(edtPrice.getText().toString());
        int image = R.drawable.aspirina_complex;

        Product product = new Product(name, description, brand, dosage, price, stock, image, cursor.getInt(0));
        */
    }

    @Override
    public void getAllCategory() {

    }

    @Override
    public void updateProduct(Product oldOne, Product newOne) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void setCursorCategory(Cursor cursor) {
        //adapterCategory.swapCursor(cursor);

        // Change closes the previous cursor and opens the new one
        adapterCategory.changeCursor(cursor);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        manageProductListener = null;
        adapterCategory = null;
    }
}
