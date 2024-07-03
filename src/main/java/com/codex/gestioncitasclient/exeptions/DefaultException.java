package com.codex.gestioncitasclient.exeptions;

public class DefaultException extends RuntimeException{
    private int code;
    private String message;

    public DefaultException(String message, int code) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }
}
