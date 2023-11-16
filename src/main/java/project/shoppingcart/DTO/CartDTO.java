package project.shoppingcart.DTO;

import project.shoppingcart.entity.CartEntity;

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
