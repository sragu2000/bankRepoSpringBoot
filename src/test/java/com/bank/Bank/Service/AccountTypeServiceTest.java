package com.bank.Bank.Service;

import com.bank.Bank.Model.AccountTypeModel;
import com.bank.Bank.Repository.AccountTypeRepository;
import com.bank.Bank.Service.AccountTypeService;
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
public class AccountTypeServiceTest {
    @Mock
    AccountTypeRepository accountTypeRepository;
    @InjectMocks
    AccountTypeService accountTypeService;


    @Test
    void createAccountType_If_Correct(){
        AccountTypeModel newAccountType = new AccountTypeModel(1, "Savings Account");
        when(accountTypeRepository.save(newAccountType)).thenReturn(newAccountType);
        ResponseEntity<AccountTypeModel> response = accountTypeService.createAccountType(newAccountType);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newAccountType, response.getBody());
        verify(accountTypeRepository, times(1)).save(newAccountType);
    }

    @Test
    void createAccountType_If_ThrowsError(){
        AccountTypeModel newAccountType = new AccountTypeModel(1, "Savings Account");
        when(accountTypeRepository.save(newAccountType)).thenThrow(new RuntimeException("Database Error"));
        ResponseEntity<AccountTypeModel> response = accountTypeService.createAccountType(newAccountType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(accountTypeRepository, times(1)).save(newAccountType);
    }
    @Test
    void getAllAccountTypes_If_Correct(){
        AccountTypeModel newAccountType = new AccountTypeModel(1, "Savings Account");
        List<AccountTypeModel> accountTypeList = new ArrayList<>();
        accountTypeList.add(newAccountType);
        when(accountTypeRepository.findAll()).thenReturn(accountTypeList);
        ResponseEntity<List<AccountTypeModel>> response = accountTypeService.getAllAccountTypes();
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(accountTypeList, response.getBody());
    }

    @Test
    void getAllAccountTypes_If_ThrowsError(){
        when(accountTypeRepository.findAll()).thenThrow(new RuntimeException("Database Error"));
        ResponseEntity<List<AccountTypeModel>> response = accountTypeService.getAllAccountTypes();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    void deleteAccountTypeById_If_ValidID(){
        AccountTypeModel newAccountType = new AccountTypeModel(1, "Savings Account");
        when(accountTypeRepository.findById(newAccountType.getId())).thenReturn(Optional.of(newAccountType));
        ResponseEntity<AccountTypeModel> response = accountTypeService.deleteAccountTypeById(newAccountType.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newAccountType, response.getBody());
    }

    @Test
    void deleteAccountTypeById_If_InValidID(){
        AccountTypeModel newAccountType = new AccountTypeModel(1, "Savings Account");
        when(accountTypeRepository.findById(newAccountType.getId())).thenReturn(Optional.empty());
        ResponseEntity<AccountTypeModel> response = accountTypeService.deleteAccountTypeById(newAccountType.getId());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteAccountTypeById_If_ThrowsError(){
        AccountTypeModel newAccountType = new AccountTypeModel(1, "Savings Account");
        when(accountTypeRepository.findById(newAccountType.getId())).thenThrow(new RuntimeException("Database Error"));
        ResponseEntity<AccountTypeModel> response = accountTypeService.deleteAccountTypeById(newAccountType.getId());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    void updateAccountById_If_ValidID(){
        AccountTypeModel newAccountType = new AccountTypeModel(1, "Savings Account");
        when(accountTypeRepository.findById(newAccountType.getId())).thenReturn(Optional.of(newAccountType));
        AccountTypeModel updatedAccountType = new AccountTypeModel(1, "Current Account");
        when(accountTypeRepository.save(newAccountType)).thenReturn(updatedAccountType);
        ResponseEntity<AccountTypeModel> response = accountTypeService.updateAccountById(newAccountType.getId(), newAccountType);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(updatedAccountType, response.getBody());
    }

    @Test
    void updateAccountById_If_InvalidID(){
        AccountTypeModel newAccountType = new AccountTypeModel(1, "Savings Account");
        when(accountTypeRepository.findById(newAccountType.getId())).thenReturn(Optional.empty());
        ResponseEntity<AccountTypeModel> response = accountTypeService.updateAccountById(newAccountType.getId(), newAccountType);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateAccountByIdIfThrowsError(){
        AccountTypeModel newAccountType = new AccountTypeModel(1, "Savings Account");
        when(accountTypeRepository.findById(newAccountType.getId())).thenThrow(new RuntimeException("Database Error"));
        ResponseEntity<AccountTypeModel> response = accountTypeService.updateAccountById(newAccountType.getId(), newAccountType);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
}
