package com.bank.Bank.Repository;
import com.bank.Bank.Model.BankModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepository extends JpaRepository<BankModel,Long>{
    BankModel save(BankModel bankModel);

    List<BankModel> findAll();
    List<BankModel> findByLocation(String location);

}