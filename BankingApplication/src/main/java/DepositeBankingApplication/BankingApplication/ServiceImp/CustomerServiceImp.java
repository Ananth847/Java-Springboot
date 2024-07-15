package DepositeBankingApplication.BankingApplication.ServiceImp;

import DepositeBankingApplication.BankingApplication.DTO.CustomerDto;

public interface CustomerServiceImp {
   public CustomerDto saveCustomer(CustomerDto customerDto);
  public  CustomerDto findByAccountNumber(String accountNumber);
   String deleteByAccountNumber(String accountNumber);
   public CustomerDto updateDetails(String accountNumber,CustomerDto customerDto);
   CustomerDto withDrawAmount(double amt,String accountNumber);
   CustomerDto depositeAmount(double amt,String accountNumber);
   CustomerDto transferAmount(double amt,String fromAccountNumber,String toAccountNumber);
}
