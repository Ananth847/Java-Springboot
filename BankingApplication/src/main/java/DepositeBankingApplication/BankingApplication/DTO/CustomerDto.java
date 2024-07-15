package DepositeBankingApplication.BankingApplication.DTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private long id;
    private String accountHolderName;
    private String accountNumber;
    private double balance;
    private String email;
    private long phonenumber;
    private String role;
    private String branch;
    private String username;
    private String password;
    private String tranasactionpass;
}