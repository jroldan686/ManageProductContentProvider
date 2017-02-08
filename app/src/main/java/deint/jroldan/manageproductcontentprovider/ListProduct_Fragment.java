package deint.jroldan.manageproductcontentprovider;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import deint.jroldan.manageproductcontentprovider.adapter.ProductAdapter;
import deint.jroldan.manageproductcontentprovider.interfaces.IProduct;
import deint.jroldan.manageproductcontentprovider.interfaces.ProductPresenter;
import deint.jroldan.manageproductcontentprovider.model.Product;
import deint.jroldan.manageproductcontentprovider.presenter.ProductPresenterImpl;

public class ListProduct_Fragment extends Fragment implements IProduct, ProductPresenter.View {

    //private ProductAdapterRecycler adapter;
    //private RecyclerView rcvProduct;
    private ListView listProduct;
    private static final int ADD_PRODUCT = 0;
    private static final int EDIT_PRODUCT = 1;
    public static String PRODUCT_KEY = "product";
    private ProductAdapter adapter;
    private ListProductListener mCallback;
    private ProductPresenter presenter;
    private TextView emptyProduct;

    public interface ListProductListener {
        void showManageProduct(Bundle bundle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_list_product, container, false);

        listProduct=(ListView)rootView.findViewById(android.R.id.list);
        listProduct.setAdapter(adapter);
        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(PRODUCT_KEY, (Product)parent.getItemAtPosition(position));
                mCallback.showManageProduct(bundle);
            }
        });
        emptyProduct = (TextView)rootView.findViewById(android.R.id.empty);
        return rootView;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Product");
        getActivity().getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    /*
     * Method which inflates the ActionBar menu
     * @param menu The menu to be inflated
     */

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_listproduct, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.action_delete_product:
                Bundle bundle = new Bundle();
                bundle.putParcelable(PRODUCT_KEY, (Product)listProduct.getItemAtPosition(info.position));
                ConfirmDialog dialog = new ConfirmDialog();
                dialog.setArguments(bundle);
                dialog.show(getActivity().getSupportFragmentManager(), "SimpleDialog");
                return true;
            break;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void showProducts(List<Product> products) {
        adapter.updateProduct(products);
    }

    public void hideList(boolean hide) {
        listProduct.setVisibility(hide ? View.GONE : View.VISIBLE);
        emptyProduct.setVisibility(hide ? View.VISIBLE : View.GONE);
    }

    public void showEmptyText(boolean show) {
        hideList(show);
    }

    public void showMessage(String message) {

    }

    void deleteObject(Object object) {
        presenter.deleteProduct((Product)object);
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
                intent = new Intent(ListProduct_Fragment.this, ManageProduct_Fragment.class);
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
                intent = new Intent(ListProduct_Fragment.this, GeneralSettingsActivity.class);
                startActivityForResult(intent, ADD_PRODUCT);
                break;
            case R.id.action_settings_account:
                intent = new Intent(ListProduct_Fragment.this, AccountSettingsActivity.class);
                startActivityForResult(intent, ADD_PRODUCT);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mCallback.showManageProduct(null);
        }
    });

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ProductAdapter(this);
        presenter = new ProductPresenterImpl(this);
        setRetainInstance(true);
        /*
         This option tells the Activity that the Fragment has its own menu and calls callback method onCreateOptionsMenu().
         */
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback=(ListProductListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(getContext().toString() + " must implement ListProductListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter = null;
        presenter = null;
    }

    @Override
    public void showMessageDelete(final Product product) {

        Snackbar.make(getView(),"Producto eliminado", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.addProduct (product);
                    }
                })

        // SetCallback (calling a SnacBar callback method, even if the SnackBar has been deleted by Swiping
        .setCallback(new Snackbar.Callback() {

            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                if((event==DISMISS_EVENT_TIMEOUT) || (event==DISMISS_EVENT_SWIPE) ||
                        event==DISMISS_EVENT_MANUAL || event==DISMISS_EVENT_CONSECUTIVE)
                    // if (event!=DISMISS_EVENT_ACTION)
                    presenter.deleteProduct(product);
            }
        }).show();
    }
}
