package com.bank.Bank.Controller;
import com.bank.Bank.Model.BankModel;
import com.bank.Bank.Repository.BankRepository;
import com.bank.Bank.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    BankRepository bankRepository;

    @Autowired
    BankService bankService;

    @PostMapping("/save")
    public ResponseEntity<BankModel> createBank(@RequestBody BankModel bankModel) {
        return bankService.createBank(bankModel);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<BankModel>> getAllBanks() {
        return bankService.getAllBanks();
    }

    @GetMapping("/get-all/location")
    public ResponseEntity<List<BankModel>> getBankByLocation(String location) {
        return bankService.getBankByLocation(location);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BankModel> deleteBankById(@PathVariable Long id) {
        return bankService.deleteBankById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BankModel> updateBankById(@PathVariable Long id, @RequestBody BankModel bankModel) {
        return bankService.updateBankById(id,bankModel);
    }
}