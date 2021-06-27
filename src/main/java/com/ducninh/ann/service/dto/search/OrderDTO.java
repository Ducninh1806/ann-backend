package com.ducninh.ann.service.dto.search;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDTO {
    public static final String DESC = "desc";
    public static final String ASC = "asc";
    private String property;
    @JsonProperty
    private boolean acsending;

    public static String getDESC() {
        return DESC;
    }

    public static String getASC() {
        return ASC;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public boolean isAcsending() {
        return acsending;
    }

    public void setAcsending(boolean acsending) {
        this.acsending = acsending;
    }

    @Override
    public String toString() {
        return "OderDTO{" +
                "property='" + property + '\'' +
                ", acsending=" + acsending +
                '}';
    }
}
