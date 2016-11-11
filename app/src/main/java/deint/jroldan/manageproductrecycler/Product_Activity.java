package deint.jroldan.manageproductrecycler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import deint.jroldan.manageproductrecycler.adapter.ProductAdapterRecycler;

public class Product_Activity extends AppCompatActivity {

    private ProductAdapterRecycler adapter;
    private RecyclerView rcvProduct;
    private static final int ADD_PRODUCT = 0;
    private static final int EDIT_PRODUCT = 1;
    private static final boolean ASC = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        adapter = new ProductAdapterRecycler(this);
        rcvProduct = (RecyclerView)findViewById(R.id.rcvProduct);
        rcvProduct.setLayoutManager(new LinearLayoutManager(this));
        rcvProduct.setAdapter(adapter);

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
                }
                break;
            case EDIT_PRODUCT:
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
