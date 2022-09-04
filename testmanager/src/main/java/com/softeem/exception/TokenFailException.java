package com.softeem.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenFailException extends AuthenticationException {
    public TokenFailException(String msg) {
        super(msg);
    }
}
