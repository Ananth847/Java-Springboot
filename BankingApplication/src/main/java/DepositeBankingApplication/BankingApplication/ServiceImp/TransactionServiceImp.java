package DepositeBankingApplication.BankingApplication.ServiceImp;

import DepositeBankingApplication.BankingApplication.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

public interface TransactionServiceImp  {
    List<Transaction> findByAccountNumberOrderByTimestampDesc(String accountNumber);
    String saveTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    List<Transaction> findByAccountNumberAndTypeOrderByTimestampDesc(String accountNumber, String type);
    List<Transaction> findDebitedTransactionsByAccountNumber(String accountNumber,String type);
    List<Transaction> findDTransferTransactionsByAccountNumber(String accountNumber,String type);
     Transaction findByReferenceNumber(String referenceNumber) ;


}
