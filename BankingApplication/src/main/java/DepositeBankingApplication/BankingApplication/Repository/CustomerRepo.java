package DepositeBankingApplication.BankingApplication.Repository;

import DepositeBankingApplication.BankingApplication.DTO.CustomerDto;
import DepositeBankingApplication.BankingApplication.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long>{
    Customer findByAccountNumber(String accountNumber);
    String deleteByAccountNumber(String accountNumber);
    public Optional<Customer> findByUsername(String username);
    Optional<Customer> findByUsernameAndPassword(String username, String password);

}
