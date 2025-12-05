package com.mpesa.wallet.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class FineractClient {
	private final RestTemplate rest = new RestTemplate();
	@Value("${fineract.baseUrl}")
	private String baseUrl;

public void postTransaction(String walletId, BigDecimal amount, String reference) {
	try {
		rest.postForLocation(baseUrl + "/fineract-ext/transactions", Map.of("walletId", walletId, "amount", amount, "reference", reference));
	} catch (Exception e) {
		// in demo we log and continue; production: handle retries and poison queue
		System.err.println("Failed to post to Fineract: " + e.getMessage());
		}
	}

public boolean checkKyc(String clientId) {
	try {
		Map resp = rest.getForObject("http://kyc-service:5000/kyc/status/" + clientId, Map.class);
		return resp != null && "PASS".equals(resp.get("status"));
	} catch (Exception e) {
		return false;
		}
	}
}