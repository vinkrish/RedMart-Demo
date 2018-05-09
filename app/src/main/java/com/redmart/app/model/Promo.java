package com.redmart.app.model;

class Promo {
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

    private double savings_amount;

    public double getSavingsAmount() {
        return this.savings_amount;
    }

    public void setSavingsAmount(double savings_amount) {
        this.savings_amount = savings_amount;
    }

    private int savings_type;

    public int getSavingsType() {
        return this.savings_type;
    }

    public void setSavingsType(int savings_type) {
        this.savings_type = savings_type;
    }

    private String savings_text;

    public String getSavingsText() {
        return this.savings_text;
    }

    public void setSavingsText(String savings_text) {
        this.savings_text = savings_text;
    }
}
