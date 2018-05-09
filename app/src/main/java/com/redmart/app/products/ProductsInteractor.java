package com.redmart.app.products;

import com.redmart.app.model.ResponseObject;

import java.util.List;

public interface ProductsInteractor {

    interface OnFinishedListener {

        void onProductsReceived(ResponseObject responseObject);

        void onError(String message);
    }

    void getProducts(int page, int pageSize, ProductsInteractor.OnFinishedListener listener);
}
