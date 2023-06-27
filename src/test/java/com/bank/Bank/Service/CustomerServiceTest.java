package com.bank.Bank.Service;

import com.bank.Bank.Model.AccountTypeModel;
import com.bank.Bank.Model.BankModel;
import com.bank.Bank.Model.CustomerModel;
import com.bank.Bank.Repository.CustomerRepository;
import com.bank.Bank.Service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepository;
    @InjectMocks
    CustomerService customerService;

    @Test
    void createCustomer_If_Correct(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        when(customerRepository.save(newCustomer)).thenReturn(newCustomer);
        ResponseEntity<CustomerModel> response = customerService.createCustomer(newCustomer);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newCustomer, response.getBody());
    }

    @Test
    void createCustomer_If_ThrowsError(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        when(customerRepository.save(newCustomer)).thenThrow(new RuntimeException(""));
        ResponseEntity<CustomerModel> response = customerService.createCustomer(newCustomer);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllCustomers_If_Correct(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        List<CustomerModel> customerModelList = new ArrayList<>();
        customerModelList.add(newCustomer);
        when(customerRepository.findAll()).thenReturn(customerModelList);
        ResponseEntity<List<CustomerModel>> response = customerService.getAllCustomers();
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customerModelList, response.getBody());

    }

    @Test
    void getAllCustomers_If_ThrowsError(){
        when(customerRepository.findAll()).thenThrow(new RuntimeException(""));
        ResponseEntity<List<CustomerModel>> response = customerService.getAllCustomers();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void findByID_If_ValidID(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        when(customerRepository.findById(newCustomer.getId())).thenReturn(Optional.of(newCustomer));
        ResponseEntity <CustomerModel> response = customerService.findByID(newCustomer.getId());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newCustomer, response.getBody());
    }

    @Test
    void findByID_If_InValidID(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        when(customerRepository.findById(newCustomer.getId())).thenReturn(Optional.empty());
        ResponseEntity <CustomerModel> response = customerService.findByID(newCustomer.getId());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void findByID_If_ThrowsError(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        when(customerRepository.findById(newCustomer.getId())).thenThrow(new RuntimeException(""));
        ResponseEntity <CustomerModel> response = customerService.findByID(newCustomer.getId());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateCustomerById_If_ValidID(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        when(customerRepository.findById(newCustomer.getId())).thenReturn(Optional.of(newCustomer));
        when(customerRepository.save(newCustomer)).thenReturn(newCustomer);
        ResponseEntity <CustomerModel> response = customerService.updateCustomerById(newCustomer.getId(), newCustomer);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newCustomer, response.getBody());
    }

    @Test
    void updateCustomerById_If_InvalidID(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        when(customerRepository.findById(newCustomer.getId())).thenReturn(Optional.empty());
        ResponseEntity <CustomerModel> response = customerService.updateCustomerById(newCustomer.getId(), newCustomer);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateCustomerById_If_ThrowsError(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        when(customerRepository.findById(newCustomer.getId())).thenThrow(new RuntimeException(""));
        ResponseEntity <CustomerModel> response = customerService.updateCustomerById(newCustomer.getId(), newCustomer);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllCustomersByName_If_Correct(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        List<CustomerModel> customerModelList = new ArrayList<>();
        customerModelList.add(newCustomer);
        when(customerRepository.findAllByName(newCustomer.getName())).thenReturn(customerModelList);
        ResponseEntity<List<CustomerModel>> response = customerService.getAllCustomersByName(newCustomer.getName());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customerModelList, response.getBody());
    }

    @Test
    void getAllCustomersByName_If_ThrowsError(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        when(customerRepository.findAllByName(newCustomer.getName())).thenThrow(new RuntimeException(""));
        ResponseEntity<List<CustomerModel>> response = customerService.getAllCustomersByName(newCustomer.getName());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllCustomersByAddress_If_Correct(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        List<CustomerModel> customerModelList = new ArrayList<>();
        customerModelList.add(newCustomer);
        when(customerRepository.findAllByAddress(newCustomer.getAddress())).thenReturn(customerModelList);
        ResponseEntity<List<CustomerModel>> response = customerService.getAllCustomersByAddress(newCustomer.getAddress());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customerModelList, response.getBody());
    }

    @Test
    void getAllCustomersByAddress_If_ThrowsError(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        when(customerRepository.findAllByAddress(newCustomer.getAddress())).thenThrow(new RuntimeException(""));
        ResponseEntity<List<CustomerModel>> response = customerService.getAllCustomersByAddress(newCustomer.getAddress());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllCustomersByNIC_If_Correct(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        List<CustomerModel> customerModelList = new ArrayList<>();
        customerModelList.add(newCustomer);
        when(customerRepository.findAllByNic(newCustomer.getNic())).thenReturn(customerModelList);
        ResponseEntity<List<CustomerModel>> response = customerService.getAllCustomersByNic(newCustomer.getNic());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customerModelList, response.getBody());
    }

    @Test
    void getAllCustomersByNIC_If_ThrowsError(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        when(customerRepository.findAllByNic(newCustomer.getNic())).thenThrow(new RuntimeException(""));
        ResponseEntity<List<CustomerModel>> response = customerService.getAllCustomersByNic(newCustomer.getNic());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteCustomerById_If_ValidID(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        when(customerRepository.findById(newCustomer.getId())).thenReturn(Optional.of(newCustomer));
        ResponseEntity <CustomerModel> response = customerService.deleteCustomerById(newCustomer.getId());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newCustomer, response.getBody());
    }

    @Test
    void deleteCustomerById_If_InvalidID(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        when(customerRepository.findById(newCustomer.getId())).thenReturn(Optional.empty());
        ResponseEntity <CustomerModel> response = customerService.deleteCustomerById(newCustomer.getId());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteCustomerById_If_ThrowsError(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        when(customerRepository.findById(newCustomer.getId())).thenThrow(new RuntimeException(""));
        ResponseEntity <CustomerModel> response = customerService.deleteCustomerById(newCustomer.getId());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllCustomersByProperties_If_NIC_Null_and_Address_null(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        List<CustomerModel> customerModelList = new ArrayList<>();
        customerModelList.add(newCustomer);
        when(customerRepository.findAllByName(newCustomer.getName())).thenReturn(customerModelList);
        ResponseEntity<List<CustomerModel>> response = customerService.getAllCustomersByProperties(newCustomer.getName(),null,null);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customerModelList, response.getBody());
    }

    @Test
    void getAllCustomersByProperties_If_Name_Null_and_Address_null(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        List<CustomerModel> customerModelList = new ArrayList<>();
        customerModelList.add(newCustomer);
        when(customerRepository.findAllByNic(newCustomer.getNic())).thenReturn(customerModelList);
        ResponseEntity<List<CustomerModel>> response = customerService.getAllCustomersByProperties(null,newCustomer.getNic(),null);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customerModelList, response.getBody());
    }

    @Test
    void getAllCustomersByProperties_If_Name_Null_and_NIC_null(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        List<CustomerModel> customerModelList = new ArrayList<>();
        customerModelList.add(newCustomer);
        when(customerRepository.findAllByAddress(newCustomer.getAddress())).thenReturn(customerModelList);
        ResponseEntity<List<CustomerModel>> response = customerService.getAllCustomersByProperties(null,null,newCustomer.getAddress());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customerModelList, response.getBody());
    }

    @Test
    void getAllCustomersByProperties_If_Address_Null(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        List<CustomerModel> customerModelList = new ArrayList<>();
        customerModelList.add(newCustomer);
        when(customerRepository.findAllByNameAndNic(newCustomer.getName(),newCustomer.getNic())).thenReturn(customerModelList);
        ResponseEntity<List<CustomerModel>> response = customerService.getAllCustomersByProperties(newCustomer.getName(), newCustomer.getNic(),null);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customerModelList, response.getBody());
    }

    @Test
    void getAllCustomersByProperties_If_NIC_Null(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        List<CustomerModel> customerModelList = new ArrayList<>();
        customerModelList.add(newCustomer);
        when(customerRepository.findAllByNameAndAddress(newCustomer.getName(),newCustomer.getAddress())).thenReturn(customerModelList);
        ResponseEntity<List<CustomerModel>> response = customerService.getAllCustomersByProperties(newCustomer.getName(), null,newCustomer.getAddress());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customerModelList, response.getBody());
    }

    @Test
    void getAllCustomersByProperties_Else(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        List<CustomerModel> customerModelList = new ArrayList<>();
        customerModelList.add(newCustomer);
        when(customerRepository.findAllByNameAndNicAndAddress(newCustomer.getName(),newCustomer.getNic(),newCustomer.getAddress())).thenReturn(customerModelList);
        ResponseEntity<List<CustomerModel>> response = customerService.getAllCustomersByProperties(newCustomer.getName(), newCustomer.getNic(),newCustomer.getAddress());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customerModelList, response.getBody());
    }

    @Test
    void getAllCustomersByProperties_If_ThrowsError(){
        BankModel newBank = new BankModel(123,"BOC","Colombo");
        CustomerModel newCustomer = new CustomerModel(12,"ABC","US","Colombo",12345, newBank);
        when(customerRepository.findAllByNameAndNicAndAddress(newCustomer.getName(),newCustomer.getNic(),newCustomer.getAddress())).thenThrow(new RuntimeException(""));
        ResponseEntity<List<CustomerModel>> response = customerService.getAllCustomersByProperties(newCustomer.getName(), newCustomer.getNic(),newCustomer.getAddress());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

}