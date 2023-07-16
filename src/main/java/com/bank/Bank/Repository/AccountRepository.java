package com.bank.bank.repository;
import com.bank.bank.domain.AccountDomain;
//import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountDomain, Long> {
    AccountDomain save(AccountDomain accountDomain);
    List<AccountDomain> findAll();

}