package com.fcesur.creditapplicationsystem.entity;

import com.fcesur.creditapplicationsystem.entity.abstracts.BaseEntity;
import com.fcesur.creditapplicationsystem.enums.CreditApplicationStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import static com.fcesur.creditapplicationsystem.enums.CreditApplicationStatusEnum.*;


@Table(name = "credit_applications")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreditApplication extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Enumerated(EnumType.STRING)
    private CreditApplicationStatusEnum status = PENDING;

    @OneToOne(mappedBy = "creditApplication")
    private Credit credit;

    private Double collateral;

}
