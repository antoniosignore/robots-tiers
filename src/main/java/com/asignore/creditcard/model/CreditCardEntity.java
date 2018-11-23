package com.asignore.creditcard.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@EqualsAndHashCode
@ToString()
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"number"})})
public class CreditCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 19, message = "Credic card number cannot have more then 19 characters")
    private String number;

    @NotNull
    @Size(min = 2, message = "Name should have atleast 2 characters")
    private String name;

    @NotNull
    private Double creditLimit;

    @NotNull
    private Double remainingCredit;
}
