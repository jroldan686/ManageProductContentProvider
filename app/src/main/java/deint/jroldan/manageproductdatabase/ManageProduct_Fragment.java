package deint.jroldan.manageproductdatabase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ManageProduct_Fragment extends AppCompatActivity {

    private EditText edtName, edtDescription, edtBrand, edtDosage, edtStock, edtPrice;
    private Button btnInsertImage, btnAddProduct;
    private boolean addAction;

    @Override
    protected void onCreateView(@Nullable Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        setContentView(R.layout.activity_manage_product);

        edtName = (EditText)findViewById(R.id.edtName);
        edtDescription = (EditText)findViewById(R.id.edtDescription);
        edtBrand = (EditText)findViewById(R.id.edtBrand);
        edtDosage = (EditText)findViewById(R.id.edtDosage);
        edtStock = (EditText)findViewById(R.id.edtStock);
        edtPrice = (EditText)findViewById(R.id.edtPrice);
        btnInsertImage = (Button)findViewById(R.id.btnInsertImage);
        btnInsertImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnAddProduct = (Button)findViewById(R.id.btnAddProduct);
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void saveProduct() {


    }
}
