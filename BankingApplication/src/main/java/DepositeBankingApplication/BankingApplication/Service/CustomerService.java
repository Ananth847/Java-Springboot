package DepositeBankingApplication.BankingApplication.Service;
import DepositeBankingApplication.BankingApplication.DTO.CustomerDto;
import DepositeBankingApplication.BankingApplication.Entity.Customer;
import DepositeBankingApplication.BankingApplication.Entity.Transaction;
import DepositeBankingApplication.BankingApplication.Mapper.AccMapper;
import DepositeBankingApplication.BankingApplication.Repository.CustomerRepo;
import DepositeBankingApplication.BankingApplication.ServiceImp.CustomerServiceImp;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class CustomerService implements CustomerServiceImp{

    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    private EmailService emailService;
    LocalDate currentDate = LocalDate.now();
    @Autowired
    TransactionService transactionService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = AccMapper.mapToCustomer(customerDto);
        customer.setAccountNumber(generateAccountNumber());
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        customer.setTranasactionpass(passwordEncoder.encode(customerDto.getTranasactionpass()));
        Customer savedCustomer = customerRepo.save(customer);
        return AccMapper.mapToCustomerDto(savedCustomer);
    }

    @Override
    public CustomerDto findByAccountNumber(String accountNumber) {
        Customer customer = customerRepo.findByAccountNumber(accountNumber);
        if (customer != null) {
            return AccMapper.mapToCustomerDto(customer);
        } else {
            return null;
        }
    }

    @Override
    public String deleteByAccountNumber(String accountNumber) {
        Customer customer = customerRepo.findByAccountNumber(accountNumber);
        if (customer != null) {
            customerRepo.delete(customer);
            return "Customer with account number " + accountNumber + " deleted successfully.";
        } else {
            return "Customer with account number " + accountNumber + " not found.";
        }
    }

    @Override
    public CustomerDto updateDetails(String accountNumber, CustomerDto customerDto) {
        Customer customer = customerRepo.findByAccountNumber(accountNumber);
      if (customer!=null){
          customer.setId(customerDto.getId());
          customer.setAccountNumber(customerDto.getAccountNumber());
          customer.setEmail(customerDto.getEmail());
          customer.setBalance(customerDto.getBalance());
          customer.setBranch(customerDto.getBranch());
          customer.setRole(customerDto.getRole());
          customer.setAccountHolderName(customerDto.getAccountHolderName());
          Customer customer1 =customerRepo.save(customer);
          return AccMapper.mapToCustomerDto(customer1);
      }
      else {
          return null;
      }
    }

    @Override
    @Transactional
    public CustomerDto withDrawAmount(double amt, String accountNumber) {
        Customer customer = customerRepo.findByAccountNumber(accountNumber);
        if (customer == null) {
            throw new NoSuchElementException("Account not found");
        }
        if (customer.getBalance() < amt){
            throw new RuntimeException("Insufficient funds");
        }
        double updatedAmt = customer.getBalance() - amt;
        customer.setBalance(updatedAmt);
        Customer updatedCustomer = customerRepo.save(customer);
        String subject = "Withdrawal Notification";
        String text=  "Hello " + customer.getAccountHolderName() + ", an amount of INR " + amt + " has been debited from your account " +
                "\n" + customer.getAccountNumber() + " on " + currentDate + ". Total available balance: INR " + customer.getBalance();
        emailService.sendTransactionNotification(customer.getEmail(), subject, text);
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(customer.getAccountNumber());
        transaction.setAmount(amt);
        transaction.setType("DEBIT");
        transaction.setBalance(customer.getBalance() - amt);
        transaction.setTimestamp(LocalDateTime.now());
        String description = "DEBIT of " + amt + " INR into account " + customer.getAccountNumber();
        transaction.setDescription(description);
        transactionService.saveTransaction(transaction);
        customer.setBalance(transaction.getBalance());
        customerRepo.save(customer);
        return AccMapper.mapToCustomerDto(updatedCustomer);
    }

    @Override
    public CustomerDto depositeAmount(double amount, String accountNumber) {
        Customer customer = customerRepo.findByAccountNumber(accountNumber);
        if (customer == null) {
            throw new NoSuchElementException("Account not found");
        }

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(customer.getAccountNumber());
        transaction.setAmount(amount);
        transaction.setType("CREDIT");
        transaction.setBalance(customer.getBalance() + amount);
        transaction.setTimestamp(LocalDateTime.now());
        String description = "Deposit of " + amount + " INR into account " + customer.getAccountNumber();
        transaction.setDescription(description);

        transactionService.saveTransaction(transaction);

        customer.setBalance(transaction.getBalance());
        customerRepo.save(customer);
        String email = customer.getEmail();
            String subject = "Deposit Notification";
      String text=  "Hello " + customer.getAccountHolderName() + ", an amount of INR " + amount + " has been credited to your account " +
                "\n" + customer.getAccountNumber() + " on " + currentDate + ". Total available balance: INR " + customer.getBalance();

        try{
                emailService.sendTransactionNotification(email, subject, text);
            } catch (Exception e) {
                e.printStackTrace();
            }


        return AccMapper.mapToCustomerDto(customer);
    }

    public CustomerDto transferAmount(double amount, String fromAccountNumber, String toAccountNumber) {
        Customer fromCustomer = customerRepo.findByAccountNumber(fromAccountNumber);
        Customer toCustomer = customerRepo.findByAccountNumber(toAccountNumber);

        if (fromCustomer == null) {
            throw new NoSuchElementException("From account not found");
        }
        if (toCustomer == null) {
            throw new NoSuchElementException("To account not found");
        }
        if (fromCustomer.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds in from account");
        }
        double updatedFromBalance = fromCustomer.getBalance() - amount;
        fromCustomer.setBalance(updatedFromBalance);
        customerRepo.save(fromCustomer);

        double updatedToBalance = toCustomer.getBalance() + amount;
        toCustomer.setBalance(updatedToBalance);
        Customer updatedToCustomer = customerRepo.save(toCustomer);
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(fromCustomer.getAccountNumber());
        transaction.setAmount(amount);
        transaction.setType("TRANSFER");
        transaction.setBalance(updatedFromBalance);
        transaction.setTimestamp(LocalDateTime.now());
        String description = "Transfer of " + amount + " INR from account " + fromCustomer.getAccountNumber() + " to account " +
                toCustomer.getAccountNumber()+" "+transaction.getReferenceNumber();
        transaction.setDescription(description);
        String referenceNumber = UUID.randomUUID().toString(); //used to generate random number
        transaction.setReferenceNumber(referenceNumber);
        transactionService.saveTransaction(transaction);
        String email = fromCustomer.getEmail();
        String email1 = toCustomer.getEmail();
        String subject = "Transfer Notification";
        String text=  "Hello " + fromCustomer.getAccountHolderName() + ",\n" +
                "An amount of INR " + amount + " has been TRANSFERRED from your account " +
                fromCustomer.getAccountNumber() + " to account " + toAccountNumber + " " +transaction.getReferenceNumber()+" "+
                "Total Available Balance: INR " + fromCustomer.getBalance();
        String subject1 ="Deposite Notification";
        String text1 = "Hello " + toCustomer.getAccountHolderName() + ",\n" +
                "An amount of INR " + amount + " has been Credited from  account " +
                fromCustomer.getAccountNumber() + " to account " + toAccountNumber + " " +" " +transaction.getReferenceNumber()+" "+
                "Total Available Balance: INR " + toCustomer.getBalance();
        try {
            emailService.sendTransactionNotification(email, subject, text);
            emailService.sendTransactionNotification(email1, subject1, text1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return AccMapper.mapToCustomerDto(updatedToCustomer);
    }

    public Optional<Customer> findByUsernameAndPassword(String username, String password) {
        Optional<Customer> customer = customerRepo.findByUsername(username);
        if (customer.isPresent() && passwordEncoder.matches(password, customer.get().getPassword())) {
            return customer;
        }
        return Optional.empty();
    }
    private String generateAccountNumber() {
        return "ACC" + System.currentTimeMillis();
    }
}
