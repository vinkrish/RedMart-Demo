package com.redmart.app.products;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.redmart.app.R;
import com.redmart.app.model.Product;
import com.redmart.app.productdetails.ProductDetailsActivity;
import com.redmart.app.util.EndlessRecyclerViewScrollListener;
import com.redmart.app.util.GridSpacingItemDecoration;
import com.redmart.app.util.NetworkUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsActivity extends AppCompatActivity implements ProductsView {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.coordinatorLayout) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private ProductsPresenter presenter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setSupportActionBar(toolbar);
        presenter = new ProductsPresenterImpl(this, new ProductsInteractorImpl());
        setupRecyclerView();
        if (NetworkUtil.isNetworkAvailable(this)) {
            presenter.getProducts(0, 20);
        } else {
            showSnackbar(getString(R.string.no_network));
        }
    }

    private void setupRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, spacingInPixels, true));

        adapter = new ProductsAdapter(this, mItemListener);
        recyclerView.setAdapter(adapter);

        scrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (NetworkUtil.isNetworkAvailable(ProductsActivity.this)) {
                    presenter.getProducts(++page, 20);
                }
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
    }

    ProductsAdapter.OnItemClickListener mItemListener = new ProductsAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(Product product) {
            Intent intent = new Intent(ProductsActivity.this, ProductDetailsActivity.class);
            intent.putExtra("id", product.getId());
            intent.putExtra("title", product.getTitle());
            startActivity(intent);
        }
    };

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        showSnackbar(getString(R.string.request_error));
    }

    private void showSnackbar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setProducts(List<Product> products) {
        if(adapter.getItemCount() > 0) adapter.updateItems(products);
        else adapter.setItems(products);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
