package com.redmart.app.model;

public class DescriptionField {

    public DescriptionField() { }

    public DescriptionField(String name, String content) {
        this.name = name;
        this.content = content;
    }

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String content;

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
