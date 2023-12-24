package com.workintech.banking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customer", schema = "fsweb")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "salary")
    private Double salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    // one olan hangisiyse onu primary key'i many olan tarafa foreign key olarak gider
    // bu yüzden many olana (i.e. referansı one olana) JoinColumn denir, foreign key columnu için
    // Address dediğimiz için onun primary key'ini alıyor (idsi). biz JoinColumn'la column'un adını belirliyoruz

    @OneToMany(cascade = {CascadeType.ALL},
            mappedBy = "customer")
    private List<Account> accounts = new ArrayList<>();
}
