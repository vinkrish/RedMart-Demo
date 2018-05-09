package com.redmart.app.model;

import java.util.ArrayList;

public class ResponseObject {
    private ArrayList<Product> products;

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    private int on_sale_count;

    public int getOnSaleCount() {
        return this.on_sale_count;
    }

    public void setOnSaleCount(int on_sale_count) {
        this.on_sale_count = on_sale_count;
    }

    private int total;

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private int page;

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    private int page_size;

    public int getPageSize() {
        return this.page_size;
    }

    public void setPageSize(int page_size) {
        this.page_size = page_size;
    }
}
