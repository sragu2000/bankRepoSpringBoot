package com.bank.bank.service.bl;

import com.bank.bank.domain.CustomerDomain;
import com.bank.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceBL {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerDomain createCustomer(CustomerDomain customerDomain) {
        return customerRepository.save(customerDomain);
    }

    public List<CustomerDomain> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerDomain findByID(long id) {
        Optional<CustomerDomain> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElse(null);
    }

    public CustomerDomain deleteCustomerById(Long id) {
        Optional<CustomerDomain> optionalCustomerModel = customerRepository.findById(id);
        if (optionalCustomerModel.isPresent()) {
            CustomerDomain presentCustomer = optionalCustomerModel.get();
            customerRepository.deleteById(id);
            return presentCustomer;
        } else {
            return null;
        }
    }

    public CustomerDomain updateCustomerById(Long id, CustomerDomain customerDomain) {
        Optional<CustomerDomain> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            CustomerDomain availableCustomer = optionalCustomer.get();
            availableCustomer.setName(customerDomain.getName());
            availableCustomer.setAddress(customerDomain.getAddress());
            availableCustomer.setNic(customerDomain.getNic());
            availableCustomer.setPhone(customerDomain.getPhone());
            availableCustomer.setBankDomain(customerDomain.getBankDomain());
            return customerRepository.save(availableCustomer);
        } else {
            return null;
        }
    }

    public List<CustomerDomain> getAllCustomersByName(String name) {
        return customerRepository.findAllByName(name);
    }

    public List<CustomerDomain> getAllCustomersByNic(String nic) {
        return customerRepository.findAllByNic(nic);
    }

    public List<CustomerDomain> getAllCustomersByAddress(String address) {
        return customerRepository.findAllByAddress(address);
    }

    public List<CustomerDomain> getAllCustomersByProperties(String name, String nic, String address) {
        if (name != null && nic == null && address == null) {
            return customerRepository.findAllByName(name);
        }
        if (name == null && nic != null && address == null) {
            return customerRepository.findAllByNic(nic);
        }
        if (name == null && nic == null && address != null) {
            return customerRepository.findAllByAddress(address);
        }
        if (name != null && nic != null && address == null) {
            return customerRepository.findAllByNameAndNic(name, nic);
        }
        if (name != null && nic == null) {
            return customerRepository.findAllByNameAndAddress(name, address);
        } else {
            return customerRepository.findAllByNameAndNicAndAddress(name, nic, address);
        }
    }
}
