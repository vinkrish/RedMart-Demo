package com.redmart.app.productdetails;

import com.redmart.app.model.Product;

public interface ProductDetailsView {

    void showProgress();

    void hideProgress();

    void showError(String message);

    void setProduct(Product product);
}
