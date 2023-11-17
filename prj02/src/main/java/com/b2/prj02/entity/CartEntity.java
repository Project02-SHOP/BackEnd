package com.b2.prj02.entity;

import lombok.Getter;

// 상품 엔터티 정의
public class CartEntity {

    @Getter
    private String name;

    @Getter
    private double price;

    public CartEntity(String name, double price){
        this.name = name;
        this.price = price;
    }

    public CartEntity() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
    }

    public void setPrice(double price) {
    }
}