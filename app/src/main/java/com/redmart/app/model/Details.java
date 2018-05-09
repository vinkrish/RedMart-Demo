package com.redmart.app.model;

class Details {
    private int prod_type;

    public int getProdType() {
        return this.prod_type;
    }

    public void setProdType(int prod_type) {
        this.prod_type = prod_type;
    }

    private String uri;

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    private int status;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private double is_new;

    public double getIsNew() {
        return this.is_new;
    }

    public void setIsNew(double is_new) {
        this.is_new = is_new;
    }

    private String storage_class;

    public String getStorageClass() {
        return this.storage_class;
    }

    public void setStorageClass(String storage_class) {
        this.storage_class = storage_class;
    }

    private String country_of_origin;

    public String getCountryOfOrigin() {
        return this.country_of_origin;
    }

    public void setCountryOfOrigin(String country_of_origin) {
        this.country_of_origin = country_of_origin;
    }
}
