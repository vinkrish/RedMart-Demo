package com.redmart.app.products;

import com.redmart.app.model.ProductsObj;

public interface ProductsInteractor {

    interface OnFinishedListener {

        void onProductsReceived(ProductsObj productsObj);

        void onError(String message);
    }

    void getProducts(int page, int pageSize, ProductsInteractor.OnFinishedListener listener);
}
