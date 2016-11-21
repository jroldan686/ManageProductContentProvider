package deint.jroldan.manageproductrecycler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import deint.jroldan.manageproductrecycler.adapter.ProductAdapter;
import deint.jroldan.manageproductrecycler.adapter.ProductAdapterRecycler;
import deint.jroldan.manageproductrecycler.interfaces.IProduct;
import deint.jroldan.manageproductrecycler.model.Product;

import static deint.jroldan.manageproductrecycler.interfaces.IProduct.PRODUCT_KEY;

public class Product_Activity extends AppCompatActivity {

    private ProductAdapter adapter;
    //private ProductAdapterRecycler adapter;
    //private RecyclerView rcvProduct;
    private ListView listProduct;
    private static final int ADD_PRODUCT = 0;
    private static final int EDIT_PRODUCT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_recycler);

        listProduct=(ListView)findViewById(R.id.listProduct);
        adapter = new ProductAdapter(this);
        listProduct.setAdapter(adapter);
        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(PRODUCT_KEY, (Product)parent.getItemAtPosition(position));
                Intent intent = new Intent(Product_Activity.this, ManageProduct_Activity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, EDIT_PRODUCT);
            }
        });
    }

    /*
     * Method which inflates the ActionBar menu
     * @param menu The menu to be inflated
     * @return true for success
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
     * @param item
     * @return true when the event controlled by this has been consumed, false when it hasn'7 and gets propagated
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_add_product:
                intent = new Intent(Product_Activity.this, ManageProduct_Activity.class);
                startActivityForResult(intent, ADD_PRODUCT);
                break;
            case R.id.action_sort_alphabetically:
                //adapter.getAllProduct(/*DAO*/);
                /* Sobreescribir metodo getAllProduct
                adapter.getAllProduct(3, ASC);
                ASC = !ASC;
                */
                break;
            case R.id.action_settings_general:
                intent = new Intent(Product_Activity.this, GeneralSettingsActivity.class);
                startActivityForResult(intent, ADD_PRODUCT);
                break;
            case R.id.action_settings_account:
                intent = new Intent(Product_Activity.this, AccountSettingsActivity.class);
                startActivityForResult(intent, ADD_PRODUCT);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ADD_PRODUCT:
                if(resultCode==RESULT_OK) {
                    //Añadir el producto
                    Product product = (Product)data.getExtras().getSerializable(PRODUCT_KEY);
                    ((ProductAdapter)listProduct.getAdapter()).addProduct(product);
                }
                break;
            case EDIT_PRODUCT:
                if(resultCode==RESULT_OK) {
                    //Product product = (Product) data.getExtras().getSerializable(PRODUCT_KEY);
                    //((ProductAdapter) listProduct.getAdapter()).addProduct(product);
                }
                break;
        }
        /*
        ASC = !ASC;
        products.clear();
        products.addAll(((ManageProductRecycler_Application)context.getApplicationContext()).getProducts(ASC));
        notifyDataSetChanged();
        */
    }
}
