package com.bank.bank.controller;

import com.bank.bank.dto.ResponseDto;
import com.bank.bank.domain.AccountDomain;
import com.bank.bank.repository.AccountRepository;
import com.bank.bank.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")

public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountService accountService;
    @PostMapping("/save")
    public ResponseDto createAccount(@Valid  @RequestBody AccountDomain accountDomain) {
        return accountService.createAccount(accountDomain);
    }

    @GetMapping("/get-all")
    public ResponseDto getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto deleteAccountById(@Valid @PathVariable Long id) {
        return accountService.deleteAccountById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDto updateAccountById(  @PathVariable Long id , @RequestBody AccountDomain accountDomain){
        return accountService.updateAccountById(id, accountDomain);
    }

    @GetMapping("/get-balance/{id}")
    public ResponseDto getBalanceById(@Valid @PathVariable Long id){
        return accountService.getBalanceById(id);
    }
}