package com.bank.Bank.Service;

import com.bank.Bank.Model.AccountTypeModel;
import com.bank.Bank.Repository.AccountRepository;
import com.bank.Bank.Repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class AccountTypeService {
    @Autowired
    AccountTypeRepository accountTypeRepository;

    public ResponseEntity<AccountTypeModel> createAccountType(AccountTypeModel accountTypeModel){
        try {
            AccountTypeModel saveAccountTypeModel = accountTypeRepository.save(accountTypeModel);
            return new ResponseEntity<>(saveAccountTypeModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<AccountTypeModel>> getAllAccountTypes() {
        try{
            List<AccountTypeModel> getAll = accountTypeRepository.findAll();
            return new ResponseEntity<>(getAll, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<AccountTypeModel> deleteAccountTypeById(@PathVariable Long id) {
        try{
            Optional<AccountTypeModel> optionalAccountType = accountTypeRepository.findById(id);
            if(optionalAccountType.isPresent()){
                accountTypeRepository.deleteById(id);
                return new ResponseEntity<>(optionalAccountType.get(),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<AccountTypeModel> updateAccountById(@PathVariable Long id , @RequestBody AccountTypeModel accountTypeModel){
        try{
            Optional<AccountTypeModel> optionalAccount = accountTypeRepository.findById(id);
            if(optionalAccount.isPresent()){
                AccountTypeModel queriedAccount = optionalAccount.get();
                queriedAccount.setAccountType(accountTypeModel.getAccountType());
                AccountTypeModel updatedAccount = accountTypeRepository.save(queriedAccount);
                return new ResponseEntity<>(updatedAccount, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
