package com.redmart.app.products;

public interface ProductsPresenter {

    void getProducts(int page, int pageSize);

    void onDestroy();
}
