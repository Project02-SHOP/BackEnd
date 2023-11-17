package com.b2.prj02.dto;

import com.b2.prj02.entity.CartEntity;

public class CartDTO extends CartEntity {
    private String name;
    private double price;

    public CartDTO(String name, double price){
        this.name = name;
        this.price = price;
    }

    public CartDTO() {

    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}