package DepositeBankingApplication.BankingApplication.Service;
import DepositeBankingApplication.BankingApplication.Entity.Transaction;
import DepositeBankingApplication.BankingApplication.Repository.TransactioRepo;
import DepositeBankingApplication.BankingApplication.ServiceImp.TransactionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements TransactionServiceImp {
    @Autowired
    TransactioRepo transactioRepo;
    @Override
    public List<Transaction> findByAccountNumberOrderByTimestampDesc(String accountNumber) {
        return transactioRepo.findByAccountNumberOrderByTimestampDesc(accountNumber);
    }
    @Override
    public String saveTransaction(Transaction transaction) {
        transactioRepo.save(transaction);
        return "Saved Transaction";
    }
    @Override
    public List<Transaction> getAllTransactions() {
        return transactioRepo.findAll();
    }
    @Override
    public List<Transaction> findByAccountNumberAndTypeOrderByTimestampDesc(String accountNumber, String type) {
        return transactioRepo.findByAccountNumberAndTypeOrderByTimestampDesc(accountNumber, "CREDIT");
    }

    @Override
    public List<Transaction> findDebitedTransactionsByAccountNumber(String accountNumber,String type) {
        return transactioRepo.findByAccountNumberAndTypeOrderByTimestampDesc(accountNumber,"Debit");
    }

    @Override
    public List<Transaction> findDTransferTransactionsByAccountNumber(String accountNumber, String type) {
        return transactioRepo.findByAccountNumberAndTypeOrderByTimestampDesc(accountNumber,"TRANSFER");

    }

    @Override
    public Transaction findByReferenceNumber(String referenceNumber) {
        return transactioRepo.findByReferenceNumber(referenceNumber);
    }

}
