package com.bank.bank.controller;
import com.bank.bank.dto.ResponseDto;
import com.bank.bank.domain.BankDomain;
import com.bank.bank.repository.BankRepository;
import com.bank.bank.service.BankService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    BankRepository bankRepository;

    @Autowired
    BankService bankServiceImpl;

    @PostMapping("/save")
    public ResponseDto createBank(@Valid  @RequestBody BankDomain bankDomain) {
        return bankServiceImpl.createBank(bankDomain);
    }

    @GetMapping("/get-all")
    public ResponseDto getAllBanks() {
        return bankServiceImpl.getAllBanks();
    }

    @GetMapping("/get-all/location")
    public ResponseDto getBankByLocation(@Valid String location) {
        return bankServiceImpl.getBankByLocation(location);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto deleteBankById(@Valid @PathVariable Long id) {
        return bankServiceImpl.deleteBankById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDto updateBankById(@Valid @PathVariable Long id, @RequestBody BankDomain bankDomain) {
        return bankServiceImpl.updateBankById(id, bankDomain);
    }
}