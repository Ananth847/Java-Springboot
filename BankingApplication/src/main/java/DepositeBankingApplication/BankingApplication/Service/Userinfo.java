package DepositeBankingApplication.BankingApplication.Service;
import DepositeBankingApplication.BankingApplication.DTO.UserInfoDetails;
import DepositeBankingApplication.BankingApplication.Entity.Customer;
import DepositeBankingApplication.BankingApplication.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Component;
import java.util.Optional;
@Component
public class Userinfo implements UserDetailsService{
    @Autowired
    CustomerRepo customerRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Customer> userinfo=customerRepo.findByUsername(username);
       return userinfo.map(UserInfoDetails::new).orElseThrow(()->new RuntimeException("user Not foud"));

    }
}
