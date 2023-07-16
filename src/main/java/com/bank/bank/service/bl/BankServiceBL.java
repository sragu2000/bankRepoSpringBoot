package com.bank.bank.service.bl;

import com.bank.bank.domain.BankDomain;
import com.bank.bank.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BankServiceBL {
    @Autowired
    BankRepository bankRepository;
    public BankDomain createBank(BankDomain bankDomain) {
        return bankRepository.save(bankDomain);
    }

    public List<BankDomain> getAllBanks() {
        return bankRepository.findAll();
    }

    public List<BankDomain> getBankByLocation(String location) {
        return bankRepository.findByLocation(location);
    }

    public BankDomain deleteBankById(Long id) {
        Optional<BankDomain> optionalBankModel = bankRepository.findById(id);
        if(optionalBankModel.isPresent()){
            BankDomain bankDeleted = optionalBankModel.get();
            bankRepository.deleteById(id);
            return bankDeleted;
        }else{
            return null;
        }
    }

    public BankDomain updateBankById(Long id, BankDomain bankDomain) {
        Optional<BankDomain> optionalBank = bankRepository.findById(id);
        System.out.println(optionalBank);
        if(optionalBank.isPresent()){
            BankDomain getBank = optionalBank.get(); // it will return bank document if found
            getBank.setName(bankDomain.getName());
            getBank.setLocation(bankDomain.getLocation());
            return bankRepository.save(getBank);
        }else{
            return null;
        }
    }
}
