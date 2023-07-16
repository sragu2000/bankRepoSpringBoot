package com.bank.bank.controller;

import com.bank.bank.dto.ResponseDto;
import com.bank.bank.domain.AccountTypeDomain;
import com.bank.bank.repository.AccountTypeRepository;
import com.bank.bank.service.AccountTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account-type")
public class AccountTypeController {
    @Autowired
    AccountTypeService accountTypeService;
    @Autowired
    AccountTypeRepository accountTypeRepository;

    @PostMapping("/save")
    public ResponseDto createAccountType(@Valid @RequestBody AccountTypeDomain accountTypeDomain){
        return accountTypeService.createAccountType(accountTypeDomain);
    }

    @GetMapping("/get-all")
    public ResponseDto getAllAccountTypes() {
        return accountTypeService.getAllAccountTypes();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto deleteAccountTypeById(@Valid @PathVariable Long id) {
        return accountTypeService.deleteAccountTypeById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDto updateAccountById( @PathVariable Long id , @RequestBody AccountTypeDomain accountTypeDomain){
        return accountTypeService.updateAccountById(id, accountTypeDomain);
    }
}
