package com.bank.bank.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class CustomerDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "nic")
    private String nic;
    @Column(name = "phone")
    private Integer phone;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private BankDomain bankDomain;
    @OneToMany(mappedBy = "customerDomain")
    private Collection<AccountDomain> accountDomain;
}
