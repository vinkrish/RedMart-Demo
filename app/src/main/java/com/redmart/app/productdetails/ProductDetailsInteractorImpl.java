package com.redmart.app.productdetails;

import com.redmart.app.api.ApiClient;
import com.redmart.app.api.DemoApi;
import com.redmart.app.model.ProductDetailObj;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsInteractorImpl implements ProductDetailsInteractor {
    @Override
    public void getProduct(int productId, final OnFinishedListener listener) {
        DemoApi demoApi = ApiClient.getClient().create(DemoApi.class);
        Call<ProductDetailObj> queue = demoApi.getProductDetails(productId);
        queue.enqueue(new Callback<ProductDetailObj>() {
            @Override
            public void onResponse(Call<ProductDetailObj> call, Response<ProductDetailObj> response) {
                if(response.isSuccessful()) {
                    listener.onProductReceived(response.body());
                } else {
                    listener.onError("");
                }
            }

            @Override
            public void onFailure(Call<ProductDetailObj> call, Throwable t) {
                listener.onError("");
            }
        });
    }
}
