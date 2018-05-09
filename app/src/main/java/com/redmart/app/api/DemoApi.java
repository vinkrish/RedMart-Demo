package com.redmart.app.api;

import com.redmart.app.model.Product;
import com.redmart.app.model.ResponseObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DemoApi {

    String Image_URL = "http://media.redmart.com/newmedia/200p";

    @GET("search")
    Call<ResponseObject> getProducts(@Query("page") int page,
                                     @Query("pageSize") int pageSize);

    @GET("products/{product_id}")
    Call<Product> getProductDetails(@Path("product_id") int product_id);
}
