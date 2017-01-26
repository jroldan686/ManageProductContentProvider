package deint.jroldan.manageproductdatabase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import deint.jroldan.manageproductdatabase.interfaces.CategoryPresenter;
import deint.jroldan.manageproductdatabase.interfaces.ManagePresenter;
import deint.jroldan.manageproductdatabase.model.Product;

public class ManageProduct_Fragment extends Fragment implements ManagePresenter.View, CategoryPresenter.View {

    private EditText edtName, edtDescription, edtBrand, edtDosage, edtStock, edtPrice;
    private Button btnInsertImage, btnAddProduct;
    private boolean addAction;

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
    }



    private void saveProduct() {


    }

    @Override
    public void getAllCategory() {

    }

    @Override
    public void saveProduct(Product product) {

    }

    @Override
    public void updateProduct(Product oldOne, Product newOne) {

    }

    @Override
    public void showMessage(String message) {

    }
}
