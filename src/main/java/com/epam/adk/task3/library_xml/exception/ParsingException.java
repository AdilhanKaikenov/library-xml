package com.epam.adk.task3.library_xml.exception;

public class ParsingException extends Exception{

    public ParsingException(String message) {
        super(message);
    }

    public ParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
