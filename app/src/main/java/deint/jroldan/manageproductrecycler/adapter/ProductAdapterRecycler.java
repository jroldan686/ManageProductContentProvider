package deint.jroldan.manageproductrecycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import deint.jroldan.manageproductrecycler.ManageProductRecycler_Application;
import deint.jroldan.manageproductrecycler.R;
import deint.jroldan.manageproductrecycler.model.Product;

/**
 * Created by usuario on 21/10/16.
 */

public class ProductAdapterRecycler extends RecyclerView.Adapter<ProductAdapterRecycler.ProductViewHolder> {

    private List<Product> products;
    private Context context;

    public ProductAdapterRecycler(Context context) {
        this.context = context;
        products=((ManageProductRecycler_Application)context.getApplicationContext()).getProducts();
    }

    @Override
    public ProductAdapterRecycler.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, null);
        return new ProductViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ProductAdapterRecycler.ProductViewHolder holder, int position) {
        holder.imvPhotoItem.setImageResource(products.get(position).getmImage());
        holder.txvNameItem.setText(products.get(position).getmName());
        holder.txvStockItem.setText(products.get(position).getFormattedUnitsInStock());
        holder.txvPriceItem.setText(products.get(position).getFormattedPrice());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView imvPhotoItem;
        TextView txvNameItem;
        TextView txvStockItem;
        TextView txvPriceItem;

        public ProductViewHolder(View item) {
            super(item);
            imvPhotoItem = (ImageView) item.findViewById(R.id.imvPhotoItem);
            txvNameItem = (TextView) item.findViewById(R.id.txvNameItem);
            txvStockItem = (TextView) item.findViewById(R.id.txvStockItem);
            txvPriceItem = (TextView) item.findViewById(R.id.txvPriceItem);
        }
    }

    public void getAllProduct(List<Product> productList) {
        products.clear();
        products.addAll(productList);
        notifyDataSetChanged();     // The view gets notified. Viewable-viewer pattern
    }
}
