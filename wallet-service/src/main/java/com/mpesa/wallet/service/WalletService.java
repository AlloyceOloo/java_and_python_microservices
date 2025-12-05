package com.mpesa.wallet.service;

import com.mpesa.wallet.model.WalletAccount;
import com.mpesa.wallet.model.WalletTransaction;
import com.mpesa.wallet.repo.WalletRepository;
import com.mpesa.wallet.client.FineractClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletService {
	private final WalletRepository walletRepository;
	private final KafkaTemplate<String, Object> kafka;
	private final FineractClient fineractClient;

	public WalletService(WalletRepository walletRepository, KafkaTemplate<String, Object> kafka, FineractClient fineractClient) {
		this.walletRepository = walletRepository;
		this.kafka = kafka;
		this.fineractClient = fineractClient;
	}

	@Transactional
	public void credit(String walletId, BigDecimal amount, String currency, String idempotencyKey) {
		// fetch or create account
		WalletAccount acct = walletRepository.findById(walletId).orElseGet(() -> {
		WalletAccount a = new WalletAccount();
		a.setId(walletId);
		a.setClientId(walletId);
		a.setCurrency(currency);
		return a;
	});

	acct.setBalance(acct.getBalance().add(amount));
	walletRepository.save(acct);

	// post to fineract (mocked)
	String txRef = "TX-" + UUID.randomUUID();
	fineractClient.postTransaction(walletId, amount, txRef);

	// publish kafka event
	kafka.send("wallet.events", txRef, Map.of("walletId", walletId, "amount", amount));
}

public void createLoan(String clientId, BigDecimal amount, int termMonths) {
	// call KYC service and then create loan event
	// In the demo the KYC service is synchronous HTTP call
	boolean kycOk = fineractClient.checkKyc(clientId);
	if (!kycOk) throw new IllegalStateException("KYC failed");

	String loanRef = "LOAN-" + UUID.randomUUID();
	kafka.send("loan.events", loanRef, Map.of("clientId", clientId, "amount", amount, "term", termMonths));
	}
}