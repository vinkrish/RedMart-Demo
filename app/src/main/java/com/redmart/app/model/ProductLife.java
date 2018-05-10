package com.redmart.app.model;

class ProductLife {
    private int time;

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    private String metric;

    public String getMetric() {
        return this.metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    private boolean is_minimum;

    public boolean getIsMinimum() {
        return this.is_minimum;
    }

    public void setIsMinimum(boolean is_minimum) {
        this.is_minimum = is_minimum;
    }

    private int time_including_delivery;

    public int getTimeIncludingDelivery() {
        return this.time_including_delivery;
    }

    public void setTimeIncludingDelivery(int time_including_delivery) {
        this.time_including_delivery = time_including_delivery;
    }
}
