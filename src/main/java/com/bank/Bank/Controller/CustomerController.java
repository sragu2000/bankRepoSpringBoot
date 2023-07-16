package com.bank.bank.controller;

import com.bank.bank.dto.ResponseDto;
import com.bank.bank.domain.CustomerDomain;
import com.bank.bank.repository.CustomerRepository;
import com.bank.bank.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @PostMapping("/save")
    public ResponseDto createCustomer(@Valid  @RequestBody CustomerDomain customerDomain) {
        return customerService.createCustomer(customerDomain);
    }

    @GetMapping("/get-all")
    public ResponseDto getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseDto findByID(@Valid @PathVariable long id){
        return customerService.findByID(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto deleteCustomerById(@Valid @PathVariable Long id) {
        return customerService.deleteCustomerById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDto updateCustomerById(@Valid @PathVariable Long id, @RequestBody CustomerDomain customerDomain) {
        return customerService.updateCustomerById(id, customerDomain);
    }

    @GetMapping("/get-all/name")
    public ResponseDto getAllCustomersByName(@Valid @RequestParam String name) {
        return customerService.getAllCustomersByName(name);
    }

    @GetMapping("/get-all/nic")
    public ResponseDto getAllCustomersByNic(@Valid @RequestParam String nic) {
        return customerService.getAllCustomersByNic(nic);
    }

    @GetMapping("/get-all/address")
    public ResponseDto getAllCustomersByAddress(@Valid @RequestParam String address) {
        return customerService.getAllCustomersByAddress(address);
    }

    @GetMapping("/get-all-by-properties")
    public ResponseDto getAllCustomersByProperties(@Valid @RequestParam(required = false) String name, @RequestParam(required = false) String nic, @RequestParam(required = false) String address) {
        return customerService.getAllCustomersByProperties(name, nic, address);
    }

}