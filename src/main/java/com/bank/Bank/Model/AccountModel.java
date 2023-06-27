package com.bank.Bank.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class AccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerModel customerModel;

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    private AccountTypeModel accountTypeModel;

    @Column(name = "balance")
    private Double balance;

}
