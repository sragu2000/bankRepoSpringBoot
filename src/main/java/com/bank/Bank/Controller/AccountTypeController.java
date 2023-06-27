package com.bank.Bank.Controller;

import com.bank.Bank.Model.AccountTypeModel;
import com.bank.Bank.Model.CustomerModel;
import com.bank.Bank.Repository.AccountTypeRepository;
import com.bank.Bank.Service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account-type")
public class AccountTypeController {
    @Autowired
    AccountTypeService accountTypeService;
    @Autowired
    AccountTypeRepository accountTypeRepository;

    @PostMapping("/save")
    public ResponseEntity<AccountTypeModel> createAccountType(@RequestBody AccountTypeModel accountTypeModel) {
        return accountTypeService.createAccountType(accountTypeModel);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<AccountTypeModel>> getAllAccountTypes() {
        return accountTypeService.getAllAccountTypes();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AccountTypeModel> deleteAccountTypeById(@PathVariable Long id) {
        return accountTypeService.deleteAccountTypeById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountTypeModel> updateAccountById(@PathVariable Long id , @RequestBody AccountTypeModel accountTypeModel){
        return accountTypeService.updateAccountById(id, accountTypeModel);
    }
}
