package com.worldline.fdm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FdmException extends Exception {
    private HttpStatus code;
    private String message;
    private String traceId;

}
