package com.bank.bank.service.bl;

import com.bank.bank.domain.AccountTypeDomain;
import com.bank.bank.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class AccountTypeServiceBL {
    @Autowired
    AccountTypeRepository accountTypeRepository;

    public AccountTypeDomain createAccountType(AccountTypeDomain accountTypeDomain){
        return accountTypeRepository.save(accountTypeDomain);

    }

    public List<AccountTypeDomain> getAllAccountTypes() {
        return accountTypeRepository.findAll();
    }

    public AccountTypeDomain deleteAccountTypeById(Long id) {
        Optional<AccountTypeDomain> optionalAccountType = accountTypeRepository.findById(id);
        if(optionalAccountType.isPresent()){
            accountTypeRepository.deleteById(id);
            return optionalAccountType.get();
        }else{
            return null;
        }

    }

    public AccountTypeDomain updateAccountById(Long id, AccountTypeDomain accountTypeDomain){
        Optional<AccountTypeDomain> optionalAccount = accountTypeRepository.findById(id);
        if(optionalAccount.isPresent()){
            AccountTypeDomain queriedAccount = optionalAccount.get();
            queriedAccount.setAccountType(accountTypeDomain.getAccountType());
            return accountTypeRepository.save(queriedAccount);
        }else{
            return null;
        }
    }
}
