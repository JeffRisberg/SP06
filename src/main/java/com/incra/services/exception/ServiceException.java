package com.incra.services.exception;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -2000964528174606492L;

    public ServiceException() {
        super();
    }
    
    public ServiceException(String message) {
        super(message); 
    }
    
    public ServiceException(Throwable throwable) {
        super(throwable); 
    }
    
    public ServiceException(String message, Throwable throwable) {
        super(message, throwable); 
    }
}
