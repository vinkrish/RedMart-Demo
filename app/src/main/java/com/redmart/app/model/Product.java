package com.redmart.app.model;

import java.util.ArrayList;

public class Product {
    private ArrayList<String> category_tags;

    public ArrayList<String> getCategoryTags() {
        return this.category_tags;
    }

    public void setCategoryTags(ArrayList<String> category_tags) {
        this.category_tags = category_tags;
    }

    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String desc;

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String sku;

    public String getSku() {
        return this.sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    private ArrayList<Integer> categories;

    public ArrayList<Integer> getCategories() {
        return this.categories;
    }

    public void setCategories(ArrayList<Integer> categories) {
        this.categories = categories;
    }

    private ArrayList<Integer> types;

    public ArrayList<Integer> getTypes() {
        return this.types;
    }

    public void setTypes(ArrayList<Integer> types) {
        this.types = types;
    }

    private Details details;

    public Details getDetails() {
        return this.details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    private ProductLife product_life;

    public ProductLife getProductLife() {
        return this.product_life;
    }

    public void setProductLife(ProductLife product_life) {
        this.product_life = product_life;
    }

    private Image image;

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    private ArrayList<Image> images;

    public ArrayList<Image> getImages() {
        return this.images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    private Measure measure;

    public Measure getMeasure() {
        return this.measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    private Pricing pricing;

    public Pricing getPricing() {
        return this.pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    private DescriptionFields description_fields;

    public DescriptionFields getDescriptionFields() {
        return this.description_fields;
    }

    public void setDescriptionFields(DescriptionFields description_fields) {
        this.description_fields = description_fields;
    }

    private int max_days_on_shelf;

    public int getMaxDaysOnShelf() {
        return this.max_days_on_shelf;
    }

    public void setMaxDaysOnShelf(int max_days_on_shelf) {
        this.max_days_on_shelf = max_days_on_shelf;
    }

    private int pr;

    public int getPr() {
        return this.pr;
    }

    public void setPr(int pr) {
        this.pr = pr;
    }

}
