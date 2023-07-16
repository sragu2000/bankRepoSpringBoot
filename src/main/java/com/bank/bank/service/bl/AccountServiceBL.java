package com.bank.bank.service.bl;

import com.bank.bank.domain.AccountDomain;
import com.bank.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceBL {
    @Autowired
    AccountRepository accountRepository;
    public AccountDomain createAccount(AccountDomain accountDomain) {
        return accountRepository.save(accountDomain);
    }

    public List<AccountDomain> getAllAccounts() {
        return accountRepository.findAll();
    }

    public AccountDomain deleteAccountById(Long id) {
        Optional<AccountDomain> optionalAccountModel= accountRepository.findById(id);
        if(optionalAccountModel.isPresent()){
            accountRepository.deleteById(id);
            return optionalAccountModel.get();
        }else{
            return null;
        }
    }

    public AccountDomain updateAccountById(Long id, AccountDomain accountDomain){
        Optional<AccountDomain> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isPresent()){
            AccountDomain availableAccount = optionalAccount.get();
            availableAccount.setCustomerDomain(accountDomain.getCustomerDomain());
            availableAccount.setAccountTypeDomain(accountDomain.getAccountTypeDomain());
            availableAccount.setBalance(accountDomain.getBalance());
            return accountRepository.save(availableAccount);
        }else{
            return null;
        }
    }

    public Double getBalanceById( Long id){
        Optional<AccountDomain> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isPresent()){
            AccountDomain availableAccount = optionalAccount.get();
            return availableAccount.getBalance();
        } else{
            return null;
        }
    }
}
