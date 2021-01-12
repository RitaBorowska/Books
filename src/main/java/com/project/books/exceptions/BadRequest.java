package com.project.books.exceptions;

public class BadRequest  extends RuntimeException{
    public BadRequest(String message){

        super("Bad request " + message);
    }
}
