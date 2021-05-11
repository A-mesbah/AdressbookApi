package de.inmediasp.adressBook.exception;

public class ApiRequestException extends  RuntimeException{
    public ApiRequestException(String message) {
        super(message);
    }
}
