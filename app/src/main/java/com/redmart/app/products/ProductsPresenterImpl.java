package com.redmart.app.products;

import com.redmart.app.model.ResponseObject;

public class ProductsPresenterImpl implements ProductsPresenter, ProductsInteractor.OnFinishedListener {
    private ProductsView mView;
    private ProductsInteractor mInteractor;

    ProductsPresenterImpl(ProductsView view, ProductsInteractor interactor) {
        mView = view;
        mInteractor = interactor;
    }

    @Override
    public void onProductsReceived(ResponseObject responseObject) {
        if(mView != null) {
            mView.hideProgress();
            mView.setProducts(responseObject.getProducts());
        }
    }

    @Override
    public void onError(String message) {
        if(mView != null) {
            mView.hideProgress();
        }
    }

    @Override
    public void getProducts(int page, int pageSize) {
        mView.showProgress();
        mInteractor.getProducts(page, pageSize, this);
    }

    @Override
    public void onDestroy() {
        mView = null;
    }
}
