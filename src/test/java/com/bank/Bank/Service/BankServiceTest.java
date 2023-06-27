package com.bank.Bank.Service;

import com.bank.Bank.Model.BankModel;
import com.bank.Bank.Repository.BankRepository;
import com.bank.Bank.Service.BankService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankServiceTest {
    @Mock
    BankRepository bankRepository;

    @InjectMocks
    BankService bankService;

    @Test
    void createBank_If_Correct(){
        BankModel newBank = new BankModel(1, "NSB", "Colombo");
        when(bankRepository.save(newBank)).thenReturn(newBank);
        ResponseEntity<BankModel> response = bankService.createBank(newBank);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newBank, response.getBody());
    }

    @Test
    void createBank_If_ThrowsError(){
        BankModel newBank = new BankModel(1, "NSB", "Colombo");
        when(bankRepository.save(newBank)).thenThrow(new RuntimeException("Database Error"));
        ResponseEntity<BankModel> response = bankService.createBank(newBank);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull( response.getBody());
    }

    @Test
    void getAllBanks_If_Correct(){
        BankModel newBank = new BankModel(1, "NSB", "Colombo");
        List<BankModel> bankList = new ArrayList<>();
        bankList.add(newBank);
        when(bankRepository.findAll()).thenReturn(bankList);
        ResponseEntity<List<BankModel>> response = bankService.getAllBanks();
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(bankList, response.getBody());
    }

    @Test
    void getAllBanks_If_ThrowsError(){
        when(bankRepository.findAll()).thenThrow(new RuntimeException("Database Error"));
        ResponseEntity<List<BankModel>> response = bankService.getAllBanks();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getBankByLocation_If_Correct(){
        BankModel newBank = new BankModel(1, "NSB", "Colombo");
        List<BankModel> bankList = new ArrayList<>();
        bankList.add(newBank);
        when(bankRepository.findByLocation(newBank.getLocation())).thenReturn(bankList);
        ResponseEntity<List<BankModel>> response = bankService.getBankByLocation(newBank.getLocation());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(bankList, response.getBody());
    }

    @Test
    void getBankByLocation_If_ThrowsError(){
        when(bankRepository.findByLocation("Kandy")).thenThrow(new RuntimeException("Database Error"));
        ResponseEntity<List<BankModel>> response = bankService.getBankByLocation("Kandy");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteBankById_If_ValidID(){
        BankModel newBank = new BankModel(1, "NSB", "Colombo");
        when(bankRepository.findById(newBank.getId())).thenReturn(Optional.of(newBank));
        ResponseEntity<BankModel> response = bankService.deleteBankById(newBank.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newBank, response.getBody());
    }

    @Test
    void deleteBankById_If_InValidID(){
        BankModel newBank = new BankModel(1, "NSB", "Colombo");
        when(bankRepository.findById(newBank.getId())).thenReturn(Optional.empty());
        ResponseEntity<BankModel> response = bankService.deleteBankById(newBank.getId());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteBankById_If_ThrowsError(){
        BankModel newBank = new BankModel(1, "NSB", "Colombo");
        when(bankRepository.findById(newBank.getId())).thenThrow(new RuntimeException("Database Error"));
        ResponseEntity<BankModel> response = bankService.deleteBankById(newBank.getId());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateBankById_If_ValidID(){
        BankModel newBank = new BankModel(1, "NSB", "Colombo");
        when(bankRepository.findById(newBank.getId())).thenReturn(Optional.of(newBank));
        BankModel updatedBankModel = new BankModel(1, "BOC", "Colombo");
        when(bankRepository.save(newBank)).thenReturn(updatedBankModel);
        ResponseEntity<BankModel> response = bankService.updateBankById(newBank.getId(), newBank);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBankModel, response.getBody());
    }

    @Test
    void updateBankById_If_InValidID(){
        BankModel newBank = new BankModel(1, "NSB", "Colombo");
        when(bankRepository.findById(newBank.getId())).thenReturn(Optional.empty());
        ResponseEntity<BankModel> response = bankService.updateBankById(newBank.getId(), newBank);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateBankById_If_ThrowsError(){
        BankModel newBank = new BankModel(1, "NSB", "Colombo");
        when(bankRepository.findById(newBank.getId())).thenThrow(new RuntimeException("Database Error"));
        ResponseEntity<BankModel> response = bankService.updateBankById(newBank.getId(), newBank);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

}