package com.mpesa.wallet.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "wallet_accounts")

public class WalletAccount {
	@Id
	private String id;
	private String clientId;
	private BigDecimal balance = BigDecimal.ZERO;
	private String currency = "KES";

	// getters/setters
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public String getClientId() { return clientId; }
	public void setClientId(String clientId) { this.clientId = clientId; }
	public BigDecimal getBalance() { return balance; }
	public void setBalance(BigDecimal balance) { this.balance = balance; }
	public String getCurrency() { return currency; }
	public void setCurrency(String currency) { this.currency = currency; }
}