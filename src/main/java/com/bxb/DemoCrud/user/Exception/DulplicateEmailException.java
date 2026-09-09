package com.bxb.DemoCrud.user.Exception;

public class DulplicateEmailException extends RuntimeException {
    public DulplicateEmailException(String message) {
        super(message);
    }
}
