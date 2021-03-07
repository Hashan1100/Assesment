package org.example.domain;

public class PurchaseResponse {
    private double netAmount;

    public PurchaseResponse(double netAmount) {
        this.netAmount = netAmount;
    }

    public double getNetAmount() {
        return netAmount;
    }
}
