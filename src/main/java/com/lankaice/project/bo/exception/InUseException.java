package com.lankaice.project.bo.exception;

public class InUseException extends RuntimeException{
    public InUseException(String message){
        super(message);
    }
}
