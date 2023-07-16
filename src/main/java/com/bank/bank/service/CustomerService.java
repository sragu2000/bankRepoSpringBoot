package com.bank.bank.service;

import com.bank.bank.dto.ResponseDto;
import com.bank.bank.domain.CustomerDomain;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface CustomerService {
    ResponseDto createCustomer(@RequestBody CustomerDomain customerDomain);

    ResponseDto getAllCustomers();

    ResponseDto findByID(@PathVariable long id);

    ResponseDto deleteCustomerById(@PathVariable Long id);

    ResponseDto updateCustomerById(@PathVariable Long id, @RequestBody CustomerDomain customerDomain);

    ResponseDto getAllCustomersByName(@RequestParam String name);

    ResponseDto getAllCustomersByNic(@RequestParam String nic);

    ResponseDto getAllCustomersByAddress(@RequestParam String address);

    ResponseDto getAllCustomersByProperties(@RequestParam(required = false) String name, @RequestParam(required = false) String nic, @RequestParam(required = false) String address);
}
