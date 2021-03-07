package org.example.domain;

public class PurchaseRequest {
    private int productId;
    private int amount;
    private boolean buyCartoon;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isBuyCartoon() {
        return buyCartoon;
    }

    public void setBuyCartoon(boolean buyCartoon) {
        this.buyCartoon = buyCartoon;
    }
}
