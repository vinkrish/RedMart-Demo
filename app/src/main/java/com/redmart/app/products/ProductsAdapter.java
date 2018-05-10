package com.redmart.app.products;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.redmart.app.R;
import com.redmart.app.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private Context context;
    private List<Product> productList;
    private final OnItemClickListener listener;

    ProductsAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.productList = new ArrayList<>();
        this.listener = listener;
    }

    interface OnItemClickListener {
        void onItemClick(Product product);
    }

    @UiThread
    public void setItems(List<Product> products) {
        this.productList = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_item, parent, false);
        return new ProductsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(productList.get(position));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_img) ImageView productImg;
        @BindView(R.id.product_title) TextView productTitle;
        @BindView(R.id.measure) TextView measure;
        @BindView(R.id.price) TextView price;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final Product product) {
            productTitle.setText(product.getTitle());
            measure.setText(product.getMeasure().getWtOrVol());
            price.setText(String.format(Locale.ENGLISH, "$%.2f", product.getPricing().getPrice()));

            Picasso.with(context)
                    .load("http://media.redmart.com/newmedia/200p" + product.getImage().getName())
                    .into(productImg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(product);
                }
            });

        }
    }
}
