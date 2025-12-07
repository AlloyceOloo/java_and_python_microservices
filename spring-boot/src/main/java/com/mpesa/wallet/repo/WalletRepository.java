package com.mpesa.wallet.repo;

import com.mpesa.wallet.model.WalletAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<WalletAccount, String> {
}