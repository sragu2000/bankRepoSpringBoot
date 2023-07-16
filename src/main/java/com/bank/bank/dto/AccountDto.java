package com.bank.bank.dto;
import com.bank.bank.domain.AccountTypeDomain;
import com.bank.bank.domain.CustomerDomain;
import lombok.Data;
@Data
public class AccountDto {
    private long id;
    private CustomerDomain customerDomain;
    private AccountTypeDomain accountTypeDomain;
    private Double balance;
}

