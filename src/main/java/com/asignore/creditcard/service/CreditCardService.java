package com.asignore.creditcard.service;

import com.asignore.creditcard.client.CreditCardValueBean;
import com.asignore.creditcard.model.CreditCardEntity;
import com.asignore.creditcard.repository.CreditcardRepository;
import com.asignore.creditcard.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CreditCardService {

    final CreditcardRepository creditcardRepository;

    public CreditCardService(CreditcardRepository creditcardRepository) {
        this.creditcardRepository = creditcardRepository;
    }

    public List<CreditCardValueBean> findAll() {
        final Collection<CreditCardEntity> all = creditcardRepository.findAll();
        return all.stream().map(MapperUtils::toValueBean).collect(Collectors.toList());
    }

    public CreditCardValueBean create(CreditCardValueBean creditCardValueBean) {
        return MapperUtils.toValueBean(creditcardRepository.save(MapperUtils.toEntityBean(creditCardValueBean)));
    }
}

