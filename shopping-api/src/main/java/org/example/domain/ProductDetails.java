package org.example.domain;

import java.util.List;

public class ProductDetails {

    public ProductDetails() { }

    private int id;

    private String name;

    private int cartoonSize;

    private int cartoonPrize;

    private double singleUnitPriceMultiplier;

    private double discountPercentage;

    private int discountSize;

    private List<UnitPrize> unitPrizes;

    public int getId() {
        return id;
    }

    public List<UnitPrize> getUnitPrizes() {
        return unitPrizes;
    }

    public void setUnitPrizes(List<UnitPrize> unitPrizes) {
        this.unitPrizes = unitPrizes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCartoonSize() {
        return cartoonSize;
    }

    public void setCartoonSize(int cartoonSize) {
        this.cartoonSize = cartoonSize;
    }

    public int getCartoonPrize() {
        return cartoonPrize;
    }

    public void setCartoonPrize(int cartoonPrize) {
        this.cartoonPrize = cartoonPrize;
    }

    public double getSingleUnitPriceMultiplier() {
        return singleUnitPriceMultiplier;
    }

    public void setSingleUnitPriceMultiplier(double singleUnitPriceMultiplier) {
        this.singleUnitPriceMultiplier = singleUnitPriceMultiplier;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getDiscountSize() {
        return discountSize;
    }

    public void setDiscountSize(int discountSize) {
        this.discountSize = discountSize;
    }

    public double calculatedSinglePrice() {
        return ((cartoonPrize / cartoonSize) * singleUnitPriceMultiplier);
    }

    public double discountedCartoonPrice() {
        return ((cartoonPrize) - (cartoonPrize * discountPercentage));
    }

    public double cartoonsFullPrize(int cartoons) {
        if (cartoons == 0) {
            return 0.0;
        } else if (discountSize <= cartoons) {
            return discountedCartoonPrice() * cartoons;
        } else {
            return cartoons * cartoonPrize;
        }
    }
}
