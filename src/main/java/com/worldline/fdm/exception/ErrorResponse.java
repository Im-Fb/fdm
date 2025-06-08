package com.worldline.fdm.exception;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ErrorResponse {

    private List<Error> errors;

    @Builder
    @Getter
    public static class Error {
        private String code;
        private String message;
        private String traceId;
        private int status;

        @Override
        public String toString() {
            return "{" +
                    "\"code\":\"" + code + "\"," +
                    "\"message\":\"" + message + "\"," +
                    "\"traceId\":\"" + traceId + "\"," +
                    "\"status\":\"" + status + "\"" +
                    "}";
        }
    }

    @Override
    public String toString() {
        return "{" +
                "\"errors\":" + errors +
                "}";
    }

    static String getErrorMsg(String errorMsg) {
        switch (errorMsg) {
            case "INTERNAL_SERVER_ERROR":
                return "Internal server error has occured";
            default:
                return "Unknown";
        }
    }
}
