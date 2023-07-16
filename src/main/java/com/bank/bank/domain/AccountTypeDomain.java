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
@Table(name = "account_type")
public class AccountTypeDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_type")
    private String accountType;

    @OneToMany(mappedBy = "accountTypeDomain")
    private Collection<AccountDomain> accountDomain;
}
