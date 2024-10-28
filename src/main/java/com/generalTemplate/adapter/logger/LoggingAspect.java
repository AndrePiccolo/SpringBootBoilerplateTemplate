package com.generalTemplate.adapter.logger;

import com.generalTemplate.adapter.rest.TransactionMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Slf4j
@Configuration
public class LoggingAspect {

    @Autowired
    private TransactionMapper transactionMapper;

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.generalTemplate..*.*(..)) && " +
            "!execution(* com.generalTemplate.adapter.rest.TransactionMapper.*(..)) && " +
            "!execution(* com.generalTemplate.adapter.rest.entity..*.*(..)) &&" +
            "!execution(* com.generalTemplate.adapter.filter.RequestFilter.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {

        logger.info("[TransactionId: {}] [SessionId: {}] Method called: {}",
                transactionMapper.singletonTransaction().getTransactionId(),
                transactionMapper.singletonTransaction().getSessionId(),
                joinPoint.getSignature());
    }
}
