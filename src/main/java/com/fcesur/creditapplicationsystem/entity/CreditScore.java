package com.fcesur.creditapplicationsystem.entity;


import com.fcesur.creditapplicationsystem.entity.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "credit_scores")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditScore extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    private Client client;

    private Integer creditScore;
}
