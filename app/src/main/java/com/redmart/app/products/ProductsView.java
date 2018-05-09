package com.redmart.app.products;

import com.redmart.app.model.Product;

import java.util.List;

public interface ProductsView {

    void showProgress();

    void hideProgress();

    void showError(String message);

    void setProducts(List<Product> products);
}
