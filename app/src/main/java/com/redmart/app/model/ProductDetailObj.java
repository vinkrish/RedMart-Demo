package com.redmart.app.model;

public class ProductDetailObj {
    private int total;

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private Product product;

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
