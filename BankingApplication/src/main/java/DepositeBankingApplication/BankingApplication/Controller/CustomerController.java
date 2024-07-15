package DepositeBankingApplication.BankingApplication.Controller;
import DepositeBankingApplication.BankingApplication.DTO.CustomerDto;
import DepositeBankingApplication.BankingApplication.Entity.Customer;
import DepositeBankingApplication.BankingApplication.Mapper.AccMapper;
import DepositeBankingApplication.BankingApplication.Repository.CustomerRepo;
import DepositeBankingApplication.BankingApplication.Service.CustomerService;
import DepositeBankingApplication.BankingApplication.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    TransactionService transactionService;
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome to our bank";
    }
    @PostMapping("/savecustomer")
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customerDto) {
        try {
            CustomerDto savedCustomer = customerService.saveCustomer(customerDto);
            return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getuser/{accountNumber}")
//    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<CustomerDto> findbyAccountNumber(@PathVariable String accountNumber) {
        try {
            CustomerDto customerDto = customerService.findByAccountNumber(accountNumber);
            return new ResponseEntity<>(customerDto, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }
    @DeleteMapping("deleteby/{accountNumber}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteByAccountNumber(@PathVariable String accountNumber) {
        try{
            return new ResponseEntity<String>( customerService.deleteByAccountNumber(accountNumber),HttpStatus.OK);
    }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        }
    @PutMapping("/updateuser/{accountNumber}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String accountNumber, @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomer = customerService.updateDetails(accountNumber, customerDto);
        if (updatedCustomer != null) {
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/withdrawamount/{accountNumber}")
    public ResponseEntity<String> withDrawAmount(@PathVariable String accountNumber, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        LocalDate currentDate = LocalDate.now();

        if (amount == null) {
            return new ResponseEntity<>("Amount must be provided", HttpStatus.BAD_REQUEST);
        }

        try {
            CustomerDto updatedCustomer = customerService.withDrawAmount(amount, accountNumber);
            String output = "Hello " + updatedCustomer.getAccountHolderName() + ", an amount of INR " + amount + " has been debited from your account " +
                    "\n" + updatedCustomer.getAccountNumber() + " on " + currentDate + ". Total available balance: INR " + updatedCustomer.getBalance();

            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Insufficient funds", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/deposit/{accountNumber}")
    public ResponseEntity<String> depositAmount(@PathVariable String accountNumber, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        if (amount == null || amount <= 0) {
            return new ResponseEntity<>("Invalid amount", HttpStatus.BAD_REQUEST);
        }

        try {
            CustomerDto updatedCustomer = customerService.depositeAmount(amount, accountNumber);
            LocalDate currentDate = LocalDate.now();
            String output = "Hello " + updatedCustomer.getAccountHolderName() + ", an amount of INR " + amount + " has been credited to your account " +
                    "\n" + updatedCustomer.getAccountNumber() + " " + currentDate + " Total Available Balance INR " + updatedCustomer.getBalance();
            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Deposit failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/transfer/{fromAccountNumber}/{toAccountNumber}")
    public ResponseEntity<String> transferAmount(
            @PathVariable String fromAccountNumber,
            @PathVariable String toAccountNumber,
            @RequestBody Map<String, Double> request) {

        Double amount = request.get("amount");
        if (amount == null || amount <= 0) {
            return new ResponseEntity<>("Invalid amount", HttpStatus.BAD_REQUEST);
        }

        try {
            CustomerDto fromCustomer = customerService.transferAmount(amount, fromAccountNumber, toAccountNumber);
            LocalDate currentDate = LocalDate.now();

            String output = "Hello " + fromCustomer.getAccountHolderName() + ",\n" +
                    "An amount of INR " + amount + " has been TRANSFERRED from your account " +
                    fromCustomer.getAccountNumber() + " to account " + toAccountNumber + " " +
                    "Total Available Balance: INR " + fromCustomer.getBalance();

            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Transfer failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<CustomerDto> loginUser(@RequestParam String username, @RequestParam String password) {
        Optional<Customer> customer = customerService.findByUsernameAndPassword(username, password);
        if (customer.isPresent()) {
            CustomerDto customerDto = AccMapper.mapToCustomerDto(customer.get());
            return new ResponseEntity<>(customerDto, HttpStatus.OK);
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

}
