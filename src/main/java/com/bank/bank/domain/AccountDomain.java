package com.bank.bank.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class AccountDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerDomain customerDomain;

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    private AccountTypeDomain accountTypeDomain;

    @Column(name = "balance")
    private Double balance;

}
