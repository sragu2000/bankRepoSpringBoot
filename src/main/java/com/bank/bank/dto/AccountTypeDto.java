package com.bank.bank.dto;
import com.bank.bank.domain.AccountDomain;
import lombok.Data;
import java.util.Collection;
@Data
public class AccountTypeDto {
    private long id;
    private String accountType;
    private Collection<AccountDomain> accountDomain;
}
