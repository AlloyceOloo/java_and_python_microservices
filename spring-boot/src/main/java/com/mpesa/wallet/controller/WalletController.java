package com.mpesa.wallet.controller;

import com.mpesa.wallet.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/wallets")

public class WalletController {
	private final WalletService walletService;
	public WalletController(WalletService walletService) { this.walletService = walletService; }

	@PostMapping("/{walletId}/credit")

	public ResponseEntity<?> credit(
		@PathVariable String walletId,
		@RequestBody Map<String, Object> body,
		@RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {

		BigDecimal amount = new BigDecimal(String.valueOf(body.get("amount")));
		String currency = (String) body.getOrDefault("currency", "KES"); 
		walletService.credit(walletId, amount, currency, idempotencyKey);
		return ResponseEntity.accepted().build();
	}

	@PostMapping("/loans")

	public ResponseEntity<?> createLoan(@RequestBody Map<String, Object> body) {
		String clientId = (String) body.get("clientId");
		BigDecimal amount = new BigDecimal(String.valueOf(body.get("amount")));
		Integer term = Integer.valueOf(String.valueOf(body.getOrDefault("termMonths", 6)));
		walletService.createLoan(clientId, amount, term);
		return ResponseEntity.ok(Map.of("status", "loan_requested"));
		}
	}