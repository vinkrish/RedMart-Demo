package com.redmart.app.productdetails;

import com.redmart.app.model.ProductDetailObj;

public interface ProductDetailsInteractor {
    interface OnFinishedListener {

        void onProductReceived(ProductDetailObj productDetailObj);

        void onError(String message);
    }

    void getProduct(int productId, ProductDetailsInteractor.OnFinishedListener listener);
}
