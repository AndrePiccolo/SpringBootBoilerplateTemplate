package com.generalTemplate.adapter.mapper;

import com.generalTemplate.adapter.rest.entity.transaction.Transaction;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TransactionMapper {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Transaction singletonTransaction(){
        Transaction transaction = new Transaction();
        transaction.setTransactionId("Internal call");
        transaction.setSessionId("Internal call");
        return transaction;
    }
}
