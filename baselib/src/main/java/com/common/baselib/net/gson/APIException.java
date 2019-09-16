package com.common.baselib.net.gson;

import java.io.IOException;

/**
 * Created by Sudroid on 16/8/16.
 */
public class APIException extends IOException {

    public int code;
    public String message;

    public APIException(){
        super();
    }


    public APIException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
