package com.bank.bank.repository;
import com.bank.bank.domain.BankDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BankRepository extends JpaRepository<BankDomain,Long>{
    BankDomain save(BankDomain bankDomain);
    List<BankDomain> findAll();
    List<BankDomain> findByLocation(String location);
}