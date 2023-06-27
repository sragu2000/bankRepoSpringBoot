package com.bank.Bank.Controller;

import com.bank.Bank.Model.AccountModel;
import com.bank.Bank.Model.BankModel;
import com.bank.Bank.Model.CustomerModel;
import com.bank.Bank.Repository.BankRepository;
import com.bank.Bank.Repository.CustomerRepository;
import com.bank.Bank.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<CustomerModel> createCustomer(@RequestBody CustomerModel customerModel) {
        return customerService.createCustomer(customerModel);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CustomerModel>> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<CustomerModel> findByID(@PathVariable long id){
        return customerService.findByID(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomerModel> deleteCustomerById(@PathVariable Long id) {
        return customerService.deleteCustomerById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerModel> updateCustomerById(@PathVariable Long id, @RequestBody CustomerModel customerModel) {
        return customerService.updateCustomerById(id, customerModel);
    }

    @GetMapping("/get-all/name")
    public ResponseEntity<List<CustomerModel>> getAllCustomersByName(@RequestParam String name) {
        return customerService.getAllCustomersByName(name);
    }

    @GetMapping("/get-all/nic")
    public ResponseEntity<List<CustomerModel>> getAllCustomersByNic(@RequestParam String nic) {
        return customerService.getAllCustomersByNic(nic);
    }

    @GetMapping("/get-all/address")
    public ResponseEntity<List<CustomerModel>> getAllCustomersByAddress(@RequestParam String address) {
        return customerService.getAllCustomersByAddress(address);
    }

    @GetMapping("/get-all-by-properties")
    public ResponseEntity<List<CustomerModel>> getAllCustomersByProperties(@RequestParam(required = false) String name, @RequestParam(required = false) String nic, @RequestParam(required = false) String address) {
        return customerService.getAllCustomersByProperties(name, nic, address);
    }

}