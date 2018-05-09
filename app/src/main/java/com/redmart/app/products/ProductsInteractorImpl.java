package com.redmart.app.products;

import com.redmart.app.api.ApiClient;
import com.redmart.app.api.DemoApi;
import com.redmart.app.model.ResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsInteractorImpl implements ProductsInteractor {
    @Override
    public void getProducts(int page, int pageSize, final OnFinishedListener listener) {
        DemoApi demoApi = ApiClient.getClient().create(DemoApi.class);
        Call<ResponseObject> queue = demoApi.getProducts(page, pageSize);
        queue.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if(response.isSuccessful()) {
                    listener.onProductsReceived(response.body());
                } else {
                    listener.onError("");
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                listener.onError("");
            }
        });
    }
}
