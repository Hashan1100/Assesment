package org.example.api;

import org.example.domain.ProductDetails;
import org.example.domain.PurchaseRequest;
import org.example.domain.PurchaseResponse;
import org.example.services.PriceEngineService;
import org.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ShoppingApiController {

	@Autowired
	private PriceEngineService priceEngineService;

	@Autowired
	private ProductService productService;

	@RequestMapping("/index")
	public String index() { return "";}

	@RequestMapping(value = "/get-products", method = RequestMethod.GET)
	public List<ProductDetails> getProducts() {
		List<ProductDetails> productDetails = productService.getProductDetails();
		return productDetails;
	}

	@PostMapping("/calculate-net-amount")
	public ResponseEntity<PurchaseResponse> calculatePurchase(@RequestBody List<PurchaseRequest> purchaseRequest) {
		PurchaseResponse purchaseResponse = priceEngineService.calculateTotalCost(purchaseRequest);
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(purchaseResponse);

	}
}