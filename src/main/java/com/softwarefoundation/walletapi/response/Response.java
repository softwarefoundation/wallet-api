package com.softwarefoundation.walletapi.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Response<T> {

    private T data;
    private List<String> errors;

    public List<String> getErrors() {
        if(this.errors == null){
            errors = new ArrayList<>();
        }
        return errors;
    }
}
