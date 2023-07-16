package com.bank.bank.service;

import com.bank.bank.dto.ResponseDto;
import com.bank.bank.domain.AccountTypeDomain;
import org.springframework.web.bind.annotation.PathVariable;

public interface AccountTypeService {
    ResponseDto createAccountType(AccountTypeDomain accountTypeDomain);

    ResponseDto getAllAccountTypes();

    ResponseDto deleteAccountTypeById(@PathVariable Long id);

    ResponseDto updateAccountById(Long id, AccountTypeDomain accountTypeDomain);
}
