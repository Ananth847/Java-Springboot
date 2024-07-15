package DepositeBankingApplication.BankingApplication.Mapper;

import DepositeBankingApplication.BankingApplication.DTO.CustomerDto;
import DepositeBankingApplication.BankingApplication.Entity.Customer;

public class AccMapper {
    public static Customer mapToCustomer(CustomerDto customerDto) {
        return new Customer(
                customerDto.getId(),
                customerDto.getAccountHolderName(),
                customerDto.getAccountNumber(),
                customerDto.getBalance(),
                customerDto.getEmail(),
                customerDto.getPhonenumber(),
                customerDto.getRole(),
                customerDto.getBranch(),
                customerDto.getUsername(),
                customerDto.getPassword(),
                customerDto.getTranasactionpass()
        );
    }

    public static CustomerDto mapToCustomerDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getAccountHolderName(),
                customer.getAccountNumber(),
                customer.getBalance(),
                customer.getEmail(),
                customer.getPhonenumber(),
                customer.getRole(),
                customer.getBranch(),
                customer.getUsername(),
                customer.getPassword(),
                customer.getTranasactionpass()
        );
    }
}
