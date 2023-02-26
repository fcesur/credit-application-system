package com.fcesur.creditapplicationsystem.entity;

import com.fcesur.creditapplicationsystem.entity.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Table(name = "credits")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Credit extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne
    private CreditApplication creditApplication;

    private Double creditLimit;
}
