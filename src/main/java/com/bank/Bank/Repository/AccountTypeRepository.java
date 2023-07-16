package com.bank.bank.repository;
import com.bank.bank.domain.AccountTypeDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountTypeRepository extends JpaRepository<AccountTypeDomain, Long> {
    AccountTypeDomain save(AccountTypeDomain accountTypeDomain);

    List<AccountTypeDomain> findAll();
}
