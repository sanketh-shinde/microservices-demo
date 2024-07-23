package com.eidiko.customer.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommonResponse<T> {

    public int status;
    private String message;
    private T data;

}
