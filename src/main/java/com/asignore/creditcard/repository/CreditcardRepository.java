package com.asignore.creditcard.repository;

import com.asignore.creditcard.model.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditcardRepository extends JpaRepository<CreditCardEntity, Long> {


}
