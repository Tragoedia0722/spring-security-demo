package com.example.springsecuritydemo.exception;

/**
 * 系统异常
 *
 * @author Tragoedia
 */
public class SystemException extends RuntimeException {
    public SystemException(String message) {
        super(message);
    }
}
