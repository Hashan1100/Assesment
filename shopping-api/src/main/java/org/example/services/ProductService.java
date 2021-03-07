package org.example.services;

import org.example.domain.ProductDetails;
import org.example.domain.PurchaseRequest;
import org.example.domain.UnitPrize;
import org.example.repo.ProductRepository;
import org.example.repo.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceEngineService priceEngineService;

    private static final List<Integer> unitPrizeList = List.of(1, 50);

    public List<ProductDetails> getProductDetails() {
        Iterable<Product> products = productRepository.findAll();
        List<ProductDetails> productDetails = toProductDetailsList(products);
        return setUnitPrizes(productDetails);
    }

    private List<ProductDetails> setUnitPrizes(List<ProductDetails> productDetails) {
        return productDetails
                .stream()
                .peek(product -> {
                    List<UnitPrize> unitPrizes = productUnitPrizes(product);
                    product.setUnitPrizes(unitPrizes);
                }).collect(Collectors.toList());
    }

    private List<ProductDetails> toProductDetailsList(Iterable<Product> products) {
        return StreamSupport
                .stream(products.spliterator(), false)
                .map(Product::toProductDetails)
                .collect(Collectors.toList());
    }

    public List<ProductDetails> getProductDetailsListFromIds(List<Integer> idList) {
        Iterable<Product> products = productRepository.findAllById(idList);
        return toProductDetailsList(products);
    }

    private List<UnitPrize> productUnitPrizes(ProductDetails productDetails) {
        return unitPrizeList
                .stream()
                .map(unitCount -> getProductUnitPrize(productDetails, unitCount))
                .collect(Collectors.toList());
    }

    private UnitPrize getProductUnitPrize(ProductDetails productDetails, Integer unitCount) {
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        purchaseRequest.setProductId(productDetails.getId());
        purchaseRequest.setAmount(unitCount);
        purchaseRequest.setBuyCartoon(false);
        double netValue = priceEngineService.calculateTotalPricePerProduct(productDetails, purchaseRequest);
        UnitPrize unitPrize = new UnitPrize();
        unitPrize.setPrize(netValue);
        unitPrize.setUnits(unitCount);
        return unitPrize;
    }
}
