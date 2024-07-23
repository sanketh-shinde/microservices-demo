package com.eidiko.customer.handler;

import com.eidiko.customer.exception.FoundException;
import com.eidiko.customer.exception.NotFoundException;
import com.eidiko.customer.exception.UnknownException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        HttpStatus httpStatus = HttpStatus.valueOf(response.status());

        if (httpStatus.is3xxRedirection()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().asInputStream()))) {
                StringBuilder responseBody = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);
                }
                return new FoundException(responseBody.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return new UnknownException("Unknown Exception Occurred");
    }
}
