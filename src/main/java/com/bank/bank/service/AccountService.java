package com.bank.bank.service;

import com.bank.bank.dto.ResponseDto;
import com.bank.bank.domain.AccountDomain;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AccountService {
    ResponseDto createAccount(@RequestBody AccountDomain accountDomain);

    ResponseDto getAllAccounts();

    ResponseDto deleteAccountById(@PathVariable Long id);

    ResponseDto updateAccountById(@PathVariable Long id, @RequestBody AccountDomain accountDomain);

    ResponseDto getBalanceById(@PathVariable Long id);
}
