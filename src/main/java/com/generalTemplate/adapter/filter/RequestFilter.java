package com.generalTemplate.adapter.filter;

import com.generalTemplate.adapter.rest.TransactionMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestFilter implements Filter {

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        transactionMapper.singletonTransaction().setTransactionId(httpServletRequest.getHeader("TransactionId"));
        transactionMapper.singletonTransaction().setSessionId(httpServletRequest.getHeader("SessionId"));
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
