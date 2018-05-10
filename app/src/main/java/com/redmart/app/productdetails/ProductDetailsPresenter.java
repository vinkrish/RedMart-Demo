package com.redmart.app.productdetails;

public interface ProductDetailsPresenter {
    void getProduct(int productId);

    void onDestroy();
}
