package com.bank.Bank.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class CustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "nic")
    private String nic;

    @Column(name = "phone")
    private Integer phone;


//    ! It means that multiple customers can be linked to the same bank.
//    * One bank can have many customers.
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private BankModel bankModel;
}
