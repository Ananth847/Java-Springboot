package DepositeBankingApplication.BankingApplication.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private long id;
        private String accountHolderName;
        private String accountNumber;
        private double balance;
        private String email;
        private long phonenumber;
        private String role;
        private String branch;
        @Column(nullable = true, unique = true)
        private String username;
        private String password;
        private String tranasactionpass;

}
