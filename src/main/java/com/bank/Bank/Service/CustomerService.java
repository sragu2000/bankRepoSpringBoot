package com.bank.Bank.Service;

import com.bank.Bank.Model.CustomerModel;
import com.bank.Bank.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public ResponseEntity<CustomerModel> createCustomer(@RequestBody CustomerModel customerModel) {
        try {
            CustomerModel saveCustomerModel = customerRepository.save(customerModel);
            return new ResponseEntity<>(saveCustomerModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CustomerModel>> getAllCustomers() {
        try {
            List<CustomerModel> getAll = customerRepository.findAll();
            return new ResponseEntity<>(getAll, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomerModel> findByID(@PathVariable long id){
        try{
            Optional<CustomerModel> optionalCustomer = customerRepository.findById(id);
            if(optionalCustomer.isPresent()){
                return new ResponseEntity<>(optionalCustomer.get(), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomerModel> deleteCustomerById(@PathVariable Long id) {
        try{
            Optional<CustomerModel> optionalCustomerModel = customerRepository.findById(id);
            if(optionalCustomerModel.isPresent()){
                CustomerModel presentCustomer = optionalCustomerModel.get();
                customerRepository.deleteById(id);
                return new ResponseEntity<>(presentCustomer, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomerModel> updateCustomerById(@PathVariable Long id, @RequestBody CustomerModel customerModel) {
        try {
            Optional<CustomerModel> optionalCustomer = customerRepository.findById(id);
            if (optionalCustomer.isPresent()) {
                CustomerModel availableCustomer = optionalCustomer.get();
                availableCustomer.setName(customerModel.getName());
                availableCustomer.setAddress(customerModel.getAddress());
                availableCustomer.setNic(customerModel.getNic());
                availableCustomer.setPhone(customerModel.getPhone());
                availableCustomer.setBankModel(customerModel.getBankModel());
                CustomerModel updatedCustomer = customerRepository.save(availableCustomer);
                return new ResponseEntity<>(updatedCustomer, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CustomerModel>> getAllCustomersByName(@RequestParam String name) {
        try {
            List<CustomerModel> getAll = customerRepository.findAllByName(name);
            return new ResponseEntity<>(getAll, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CustomerModel>> getAllCustomersByNic(@RequestParam String nic) {
        try {
            List<CustomerModel> getAll = customerRepository.findAllByNic(nic);
            return new ResponseEntity<>(getAll, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CustomerModel>> getAllCustomersByAddress(@RequestParam String address) {
        try {
            List<CustomerModel> getAll = customerRepository.findAllByAddress(address);
            return new ResponseEntity<>(getAll, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CustomerModel>> getAllCustomersByProperties(@RequestParam(required = false) String name, @RequestParam(required = false) String nic, @RequestParam(required = false) String address) {
        try {
            if (name != null && nic == null && address == null) {
                List<CustomerModel> getAll = customerRepository.findAllByName(name);
                return new ResponseEntity<>(getAll, HttpStatus.CREATED);
            }
            if (name == null && nic != null && address == null) {
                List<CustomerModel> getAll = customerRepository.findAllByNic(nic);
                return new ResponseEntity<>(getAll, HttpStatus.CREATED);
            }
            if (name == null && nic == null && address != null) {
                List<CustomerModel> getAll = customerRepository.findAllByAddress(address);
                return new ResponseEntity<>(getAll, HttpStatus.CREATED);
            }
            if (name != null && nic != null && address == null) {
                List<CustomerModel> getAll = customerRepository.findAllByNameAndNic(name, nic);
                return new ResponseEntity<>(getAll, HttpStatus.CREATED);
            }
            if (name != null && nic == null) {
                List<CustomerModel> getAll = customerRepository.findAllByNameAndAddress(name, address);
                return new ResponseEntity<>(getAll, HttpStatus.CREATED);
            } else {
                List<CustomerModel> getAll = customerRepository.findAllByNameAndNicAndAddress(name, nic, address);
                return new ResponseEntity<>(getAll, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
