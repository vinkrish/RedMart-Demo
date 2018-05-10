package com.redmart.app.model;

import java.util.ArrayList;

public class DescriptionFields {
    private ArrayList<DescriptionField> secondary;

    public ArrayList<DescriptionField> getSecondary() {
        return this.secondary;
    }

    public void setSecondary(ArrayList<DescriptionField> secondary) {
        this.secondary = secondary;
    }

    private ArrayList<DescriptionField> primary;

    public ArrayList<DescriptionField> getPrimary() {
        return this.primary;
    }

    public void setPrimary(ArrayList<DescriptionField> primary) {
        this.primary = primary;
    }
}
