package com.codecrafter.spring_with_flutter.base.rs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDataWrapper<T> {
    private String resultCode;
    private String msg;
    private T data;

    public static <T> ResponseDataWrapper<T> of(String resultCode, String msg, T data) {
        return new ResponseDataWrapper<>(resultCode, msg, data);
    }

}
