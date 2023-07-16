package com.bank.bank.dto;

import com.bank.bank.domain.AccountDomain;
import com.bank.bank.domain.BankDomain;
import lombok.Data;

import java.util.Collection;

@Data
public class CustomerDto {
    private long id;
    private String name;
    private String address;
    private String nic;
    private Integer phone;
    private BankDomain bankDomain;
    private Collection<AccountDomain> accountDomain;
}
