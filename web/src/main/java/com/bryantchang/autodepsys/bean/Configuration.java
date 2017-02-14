package com.bryantchang.autodepsys.bean;


import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


import java.util.List;


/**
 * Created by bryantchang on 2017/2/14.
 */
@Root
public class Configuration {
    @ElementList(inline = true)
    private List<Property> propertyList;


    public Configuration() {
    }

    public Configuration(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }
}


