package com.mpesa.wallet.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "wallet_transactions")
	public class WalletTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String walletId;
	private BigDecimal amount;
	private String type; // CREDIT or DEBIT
	private String reference;
	private OffsetDateTime createdAt = OffsetDateTime.now();
		
	// getters/setters
	public Long getId() { return id; }
	public String getWalletId() { return walletId; }
	public void setWalletId(String walletId) { this.walletId = walletId; }
	public BigDecimal getAmount() { return amount; }
	public void setAmount(BigDecimal amount) { this.amount = amount; }
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }
	public String getReference() { return reference; }
	public void setReference(String reference) { this.reference = reference; }
	public OffsetDateTime getCreatedAt() { return createdAt; }
}