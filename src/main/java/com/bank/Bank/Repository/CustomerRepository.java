package com.bank.Bank.Repository;

import com.bank.Bank.Model.CustomerModel;
//import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
    CustomerModel save(CustomerModel customerModel);
    List<CustomerModel> findAll();
    List<CustomerModel> findAllByName(String name);
    List<CustomerModel> findAllByNic(String nic);
    List<CustomerModel> findAllByAddress(String address);
    List<CustomerModel> findAllByNameAndNicAndAddress(String name,String nic, String address);

    List<CustomerModel> findAllByNameAndNic(String name, String nic);

    List<CustomerModel> findAllByNameAndAddress(String name, String address);
}
