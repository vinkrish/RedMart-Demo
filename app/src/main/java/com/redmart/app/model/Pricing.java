package com.redmart.app.model;

public class Pricing {
    private int on_sale;

    public int getOnSale() {
        return this.on_sale;
    }

    public void setOnSale(int on_sale) {
        this.on_sale = on_sale;
    }

    private double price;

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double promo_price;

    public double getPromoPrice() {
        return this.promo_price;
    }

    public void setPromoPrice(double promo_price) {
        this.promo_price = promo_price;
    }

    private double savings;

    public double getSavings() {
        return this.savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    private Integer savings_type;

    public Integer getSavingsType() {
        return this.savings_type;
    }

    public void setSavingsType(Integer savings_type) {
        this.savings_type = savings_type;
    }

    private Double savings_amount;

    public Double getSavingsAmount() {
        return this.savings_amount;
    }

    public void setSavingsAmount(Double savings_amount) {
        this.savings_amount = savings_amount;
    }

    private String savings_text;

    public String getSavingsText() {
        return this.savings_text;
    }

    public void setSavingsText(String savings_text) {
        this.savings_text = savings_text;
    }

    private Integer promo_id;

    public Integer getPromoId() {
        return this.promo_id;
    }

    public void setPromoId(Integer promo_id) {
        this.promo_id = promo_id;
    }

    private Discounts discounts;

    public Discounts getDiscounts() {
        return this.discounts;
    }

    public void setDiscounts(Discounts discounts) {
        this.discounts = discounts;
    }

    private String applicable_discount;

    public String getApplicableDiscount() {
        return this.applicable_discount;
    }

    public void setApplicableDiscount(String applicable_discount) {
        this.applicable_discount = applicable_discount;
    }
}
