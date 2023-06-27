package com.bank.Bank.Service;

import com.bank.Bank.Model.AccountModel;
import com.bank.Bank.Model.AccountTypeModel;
import com.bank.Bank.Model.BankModel;
import com.bank.Bank.Model.CustomerModel;
import com.bank.Bank.Repository.AccountRepository;
import com.bank.Bank.Service.AccountService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountService accountService;

    @Test
    void createAccount_If_Correct() {
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        AccountTypeModel newAccountType = new AccountTypeModel(12, "Current");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        AccountModel newAccount = new AccountModel(12, newCustomer, newAccountType, 34000.0);
        when(accountRepository.save(newAccount)).thenReturn(newAccount);
        ResponseEntity<AccountModel> response = accountService.createAccount(newAccount);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newAccount, response.getBody());
    }

    @Test
    void createAccount_If_ThrowsError(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        AccountTypeModel newAccountType = new AccountTypeModel(12, "Current");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        AccountModel newAccount = new AccountModel(12, newCustomer, newAccountType, 34000.0);
        when(accountRepository.save(newAccount)).thenThrow(new RuntimeException("Database Error"));
        ResponseEntity<AccountModel> response = accountService.createAccount(newAccount);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllAccounts_If_Correct(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        AccountTypeModel newAccountType = new AccountTypeModel(12, "Current");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        AccountModel newAccount = new AccountModel(12, newCustomer, newAccountType, 34000.0);
        List<AccountModel> accountList = new ArrayList<>();
        accountList.add(newAccount);
        when(accountRepository.findAll()).thenReturn(accountList);
        ResponseEntity<List<AccountModel>> response = accountService.getAllAccounts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accountList, response.getBody());
    }

    @Test
    void getAllAccounts_If_ThrowsError(){
        when(accountRepository.findAll()).thenThrow(new RuntimeException(""));
        ResponseEntity<List<AccountModel>> response = accountService.getAllAccounts();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateAccountById_If_ValidID(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        AccountTypeModel newAccountType = new AccountTypeModel(12, "Current");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        AccountModel newAccount = new AccountModel(12, newCustomer, newAccountType, 34000.0);
        when(accountRepository.findById(newAccount.getId())).thenReturn(Optional.of(newAccount));
        when(accountRepository.save(newAccount)).thenReturn(newAccount);
        ResponseEntity<AccountModel> response = accountService.updateAccountById(newAccount.getId(), newAccount);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newAccount, response.getBody());
    }

    @Test
    void updateAccountById_If_InvalidID(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        AccountTypeModel newAccountType = new AccountTypeModel(12, "Current");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        AccountModel newAccount = new AccountModel(12, newCustomer, newAccountType, 34000.0);
        when(accountRepository.findById(newAccount.getId())).thenReturn(Optional.empty());
        ResponseEntity<AccountModel> response = accountService.updateAccountById(newAccount.getId(), newAccount);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateAccountById_If_ThrowsError(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        AccountTypeModel newAccountType = new AccountTypeModel(12, "Current");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        AccountModel newAccount = new AccountModel(12, newCustomer, newAccountType, 34000.0);
        when(accountRepository.findById(newAccount.getId())).thenThrow(new RuntimeException(""));
        ResponseEntity<AccountModel> response = accountService.updateAccountById(newAccount.getId(), newAccount);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    void DeleteAccountById_If_ValidID(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        AccountTypeModel newAccountType = new AccountTypeModel(12, "Current");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        AccountModel newAccount = new AccountModel(12, newCustomer, newAccountType, 34000.0);
        when(accountRepository.findById(newAccount.getId())).thenReturn(Optional.of(newAccount));
        ResponseEntity<AccountModel> response = accountService.deleteAccountById(newAccount.getId());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newAccount, response.getBody());
    }

    @Test
    void DeleteAccountById_If_InValidID(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        AccountTypeModel newAccountType = new AccountTypeModel(12, "Current");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        AccountModel newAccount = new AccountModel(12, newCustomer, newAccountType, 34000.0);
        when(accountRepository.findById(newAccount.getId())).thenReturn(Optional.empty());
        ResponseEntity<AccountModel> response = accountService.deleteAccountById(newAccount.getId());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void DeleteAccountById_If_ThrowsError(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        AccountTypeModel newAccountType = new AccountTypeModel(12, "Current");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        AccountModel newAccount = new AccountModel(12, newCustomer, newAccountType, 34000.0);
        when(accountRepository.findById(newAccount.getId())).thenThrow(new RuntimeException(""));
        ResponseEntity<AccountModel> response = accountService.deleteAccountById(newAccount.getId());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getBalanceById_If_ValidID(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        AccountTypeModel newAccountType = new AccountTypeModel(12, "Current");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        AccountModel newAccount = new AccountModel(12, newCustomer, newAccountType, 34000.0);
        when(accountRepository.findById(newAccount.getId())).thenReturn(Optional.of(newAccount));
        ResponseEntity<Double> response = accountService.getBalanceById(newAccount.getId());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newAccount.getBalance(), response.getBody());
    }

    @Test
    void getBalanceById_If_InvalidID(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        AccountTypeModel newAccountType = new AccountTypeModel(12, "Current");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        AccountModel newAccount = new AccountModel(12, newCustomer, newAccountType, 34000.0);
        when(accountRepository.findById(newAccount.getId())).thenReturn(Optional.empty());
        ResponseEntity<Double> response = accountService.getBalanceById(newAccount.getId());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getBalanceById_If_ThrowsError(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        AccountTypeModel newAccountType = new AccountTypeModel(12, "Current");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        AccountModel newAccount = new AccountModel(12, newCustomer, newAccountType, 34000.0);
        when(accountRepository.findById(newAccount.getId())).thenThrow(new RuntimeException(""));
        ResponseEntity<Double> response = accountService.getBalanceById(newAccount.getId());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
}