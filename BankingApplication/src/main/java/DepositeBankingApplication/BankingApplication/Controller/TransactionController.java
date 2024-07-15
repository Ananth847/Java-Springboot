package DepositeBankingApplication.BankingApplication.Controller;
import DepositeBankingApplication.BankingApplication.Entity.Transaction;
import DepositeBankingApplication.BankingApplication.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("transaction")
public class TransactionController{
    @Autowired
    private TransactionService transactionService;
    @GetMapping("/getalltransactions/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountNumber(@PathVariable String accountNumber) {
        return new ResponseEntity<>(transactionService.findByAccountNumberOrderByTimestampDesc(accountNumber), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getalltransactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
         return new ResponseEntity<>(transactionService.getAllTransactions(),HttpStatus.OK);
    }
    @GetMapping("trans/credited/{accountNumber}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Transaction>> getCreditedTransactionsByAccountNumber(@PathVariable String accountNumber) {
        return new ResponseEntity<>(transactionService.findByAccountNumberAndTypeOrderByTimestampDesc(accountNumber,"CREDIT"),
                HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("trans/debited/{accountNumber}")
    public ResponseEntity<List<Transaction>> findDebitedTransactionsByAccountNumber(@PathVariable String accountNumber) {
        return new ResponseEntity<>(transactionService.findDebitedTransactionsByAccountNumber(accountNumber,"DEBIT"),
                HttpStatus.OK);
    }
    @GetMapping("trans/transfer/{accountNumber}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Transaction>> getTransferTransactions(@PathVariable String accountNumber) {
        return new ResponseEntity<>(transactionService.findDebitedTransactionsByAccountNumber(accountNumber,"TRANSFER"),
                HttpStatus.OK);
    }
    @GetMapping("trans/transaction/{referenceNumber}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Transaction> getTransactionByReferenceNumber(@PathVariable String referenceNumber) {
        Transaction transaction = transactionService.findByReferenceNumber(referenceNumber);
        if (transaction == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
    }
