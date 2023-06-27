package com.bank.Bank.Controller;

import com.bank.Bank.Model.AccountModel;
import com.bank.Bank.Repository.AccountRepository;
import com.bank.Bank.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/account")

public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountService accountService;
    @PostMapping("/save")
    public ResponseEntity<AccountModel> createAccount(@RequestBody AccountModel accountModel) {
        return accountService.createAccount(accountModel);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<AccountModel>> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AccountModel> deleteAccountById(@PathVariable Long id) {
        return accountService.deleteAccountById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountModel> updateAccountById( @PathVariable Long id , @RequestBody AccountModel accountModel){
        return accountService.updateAccountById(id, accountModel);
    }

    @GetMapping("/get-balance/{id}")
    public ResponseEntity<Double> getBalanceById(@PathVariable Long id){
        return accountService.getBalanceById(id);
    }
}