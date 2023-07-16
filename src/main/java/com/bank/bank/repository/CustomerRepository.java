package com.bank.bank.repository;

import com.bank.bank.domain.CustomerDomain;
//import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerDomain, Long> {
    CustomerDomain save(CustomerDomain customerDomain);
    List<CustomerDomain> findAll();
    List<CustomerDomain> findAllByName(String name);
    List<CustomerDomain> findAllByNic(String nic);
    List<CustomerDomain> findAllByAddress(String address);
    List<CustomerDomain> findAllByNameAndNicAndAddress(String name, String nic, String address);

    List<CustomerDomain> findAllByNameAndNic(String name, String nic);

    List<CustomerDomain> findAllByNameAndAddress(String name, String address);

}
