package com.qdbank.mall.error;

import org.springframework.security.core.AuthenticationException;

public class RepeatLoginException extends AuthenticationException {

    public RepeatLoginException(String msg) {
        super(msg);
    }
}
