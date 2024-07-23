package com.eidiko.product.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommonResponse<T> {

    private int status;
    private String message;
    private T data;

}
