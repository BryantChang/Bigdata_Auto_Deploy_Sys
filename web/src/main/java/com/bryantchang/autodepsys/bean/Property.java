package com.bryantchang.autodepsys.bean;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by bryantchang on 2017/2/14.
 */
@Root
public class Property {
    @Element
    private String name;

    @Element
    private String value;

    @Element(required = false)
    private String description = "";

    public Property() {
    }

    public Property(String name, String value, String description) {
        this.name = name;
        this.value = value;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
