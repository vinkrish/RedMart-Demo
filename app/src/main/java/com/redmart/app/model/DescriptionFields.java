package com.redmart.app.model;

import java.util.ArrayList;

class DescriptionFields {
    private ArrayList<Secondary> secondary;

    public ArrayList<Secondary> getSecondary() {
        return this.secondary;
    }

    public void setSecondary(ArrayList<Secondary> secondary) {
        this.secondary = secondary;
    }

    private ArrayList<Primary> primary;

    public ArrayList<Primary> getPrimary() {
        return this.primary;
    }

    public void setPrimary(ArrayList<Primary> primary) {
        this.primary = primary;
    }
}
