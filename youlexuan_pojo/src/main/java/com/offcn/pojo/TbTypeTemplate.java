package com.offcn.pojo;

import java.io.Serializable;

public class TbTypeTemplate implements Serializable{
    private Long id;

    private String name;

    private String specIds;

    private String brandIds;

    private String customAttributeItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecIds() {
        return specIds;
    }

    public void setSpecIds(String specIds) {
        this.specIds = specIds;
    }

    public String getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(String brandIds) {
        this.brandIds = brandIds;
    }

    public String getCustomAttributeItems() {
        return customAttributeItems;
    }

    public void setCustomAttributeItems(String customAttributeItems) {
        this.customAttributeItems = customAttributeItems;
    }

    public TbTypeTemplate(Long id, String name, String specIds, String brandIds, String customAttributeItems) {
        this.id = id;
        this.name = name;
        this.specIds = specIds;
        this.brandIds = brandIds;
        this.customAttributeItems = customAttributeItems;
    }

    public TbTypeTemplate() {
    }

    @Override
    public String toString() {
        return "TbTypeTemplate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specIds='" + specIds + '\'' +
                ", brandIds='" + brandIds + '\'' +
                ", customAttributeItems='" + customAttributeItems + '\'' +
                '}';
    }
}