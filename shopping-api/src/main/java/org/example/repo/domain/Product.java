package org.example.repo.domain;

import org.example.domain.ProductDetails;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;

    @Column(name = "cartoon_size")
    private int cartoonSize;

    @Column(name = "cartoon_prize")
    private int cartoonPrize;

    @Column(name = "single_unit_price_multiplier")
    private double singleUnitPriceMultiplier;

    @Column(name = "discount_percentage")
    private double discountPercentage;

    @Column(name = "discount_size")
    private int discountSize;

    @Column(name = "created_time")
    private Instant createdTime;

    @Column(name = "updated_time")
    private Instant updatedTime;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
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

    @Column(name = "cartoon_size")
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

    public Instant getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public Instant getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Instant updatedTime) {
        this.updatedTime = updatedTime;
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

    public ProductDetails toProductDetails() {
        ProductDetails productDetails = new ProductDetails();
        productDetails.setId(id);
        productDetails.setCartoonSize(cartoonSize);
        productDetails.setCartoonPrize(this.cartoonPrize);
        productDetails.setDiscountPercentage(this.discountPercentage);
        productDetails.setDiscountSize(this.discountSize);
        productDetails.setName(this.name);
        productDetails.setSingleUnitPriceMultiplier(this.singleUnitPriceMultiplier);
        return productDetails;
    }
}
