package org.example.services;

import org.example.domain.ProductDetails;
import org.example.domain.PurchaseRequest;
import org.example.domain.PurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriceEngineService {

    @Autowired
    private ProductService productService;

    public PurchaseResponse calculateTotalCost(List<PurchaseRequest> purchaseRequestList) {
        List<ProductDetails> productDetailsListFromIds = getProductDetailsListFromIds(purchaseRequestList);
        double totalValue = productDetailsListFromIds
                .stream()
                .map(productDetails -> {
                    PurchaseRequest purchaseRequest = findPurchaseRequest(purchaseRequestList, productDetails);
                    if (purchaseRequest != null) {
                        return calculateTotalPricePerProduct(productDetails, purchaseRequest);
                    } else {
                        return 0.0;
                    }
                }).reduce(0.0, Double::sum);
        return new PurchaseResponse(totalValue);
    }

    private PurchaseRequest findPurchaseRequest(List<PurchaseRequest> purchaseRequestList, ProductDetails productDetails) {
        return purchaseRequestList
                .stream()
                .filter(purchaseRequest -> isProduct(productDetails, purchaseRequest))
                .findFirst()
                .orElse(null);
    }

    private boolean isProduct(ProductDetails productDetails, PurchaseRequest purchaseRequest) {
        return purchaseRequest.getProductId() == productDetails.getId();
    }

    private List<ProductDetails> getProductDetailsListFromIds(List<PurchaseRequest> purchaseRequestList) {
        List<Integer> idList = purchaseRequestList
                .stream()
                .map(PurchaseRequest::getProductId)
                .collect(Collectors
                        .toList());
        return productService.getProductDetailsListFromIds(idList);
    }

    public Double calculateTotalPricePerProduct(ProductDetails productDetails, PurchaseRequest purchaseRequest1) {
        if (purchaseRequest1.isBuyCartoon()) {
            return handleCartoonOnly(productDetails, purchaseRequest1);
        } else {
            return handleSinglePurchase(productDetails, purchaseRequest1);
        }
    }

    private Double handleSinglePurchase(ProductDetails productDetails, PurchaseRequest purchaseRequest1) {
        int cartoons = purchaseRequest1.getAmount() / productDetails.getCartoonSize();
        double prizeForCartoons = productDetails.cartoonsFullPrize(cartoons);
        int singleUnits = purchaseRequest1.getAmount() % productDetails.getCartoonSize();
        double singleUnitPrize = singleUnits * productDetails.calculatedSinglePrice();
        return (prizeForCartoons + singleUnitPrize);
    }

    private Double handleCartoonOnly(ProductDetails productDetails, PurchaseRequest purchaseRequest1) {
        int cartoons = purchaseRequest1.getAmount();
        double prizeForCartoons = productDetails.cartoonsFullPrize(cartoons);
        return prizeForCartoons;
    }
}
