package com.fcesur.creditapplicationsystem.entity;

import com.fcesur.creditapplicationsystem.entity.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


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

    @OneToOne(cascade = CascadeType.ALL)
    private CreditApplication creditApplication;

    private Double creditLimit;
    
}
