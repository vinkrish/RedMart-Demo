package com.redmart.app.api;

import com.redmart.app.model.Product;
import com.redmart.app.model.ProductDetailObj;
import com.redmart.app.model.ProductsObj;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DemoApi {

    String Image_URL = "http://media.redmart.com/newmedia/200p";

    @GET("search")
    Call<ProductsObj> getProducts(@Query("page") int page,
                                  @Query("pageSize") int pageSize);

    @GET("products/{product_id}")
    Call<ProductDetailObj> getProductDetails(@Path("product_id") int product_id);
}
