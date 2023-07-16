package com.bank.bank.service;

import com.bank.bank.dto.ResponseDto;
import com.bank.bank.domain.BankDomain;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface BankService {
    ResponseDto createBank(@RequestBody BankDomain bankDomain);

    ResponseDto getAllBanks();

    ResponseDto getBankByLocation(String location);

    ResponseDto deleteBankById(@PathVariable Long id);

    ResponseDto updateBankById(@PathVariable Long id, @RequestBody BankDomain bankDomain);
}
