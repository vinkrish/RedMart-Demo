package com.redmart.app.productdetails;

import com.redmart.app.model.ProductDetailObj;

public class ProductDetailsPresenterImpl implements ProductDetailsPresenter, ProductDetailsInteractor.OnFinishedListener {
    private ProductDetailsView mView;
    private ProductDetailsInteractor mIneractor;

    ProductDetailsPresenterImpl(ProductDetailsView view, ProductDetailsInteractor interactor) {
        mView = view;
        mIneractor = interactor;
    }

    @Override
    public void getProduct(int productId) {
        mIneractor.getProduct(productId, this);
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void onProductReceived(ProductDetailObj productDetailObj) {
        if(mView != null) {
            mView.setProduct(productDetailObj.getProduct());
        }
    }

    @Override
    public void onError(String message) {
        if(mView != null) {
            mView.showError(message);
        }
    }
}
