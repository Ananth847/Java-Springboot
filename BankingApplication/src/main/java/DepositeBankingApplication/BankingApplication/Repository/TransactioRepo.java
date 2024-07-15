package DepositeBankingApplication.BankingApplication.Repository;

import DepositeBankingApplication.BankingApplication.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactioRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountNumberOrderByTimestampDesc(String accountNumber);
    List<Transaction> findByAccountNumberAndTypeOrderByTimestampDesc(String accountNumber, String type);
    Transaction findByReferenceNumber(String referenceNumber);



}
