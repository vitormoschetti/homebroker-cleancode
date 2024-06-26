package br.com.portfolio.application.shared.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private final int statusCode;
    private final List<String> messages;
    final boolean success;
    private final T data;

    private Response(HttpStatus statusCode, List<String> messages, boolean success, T data) {
        this.statusCode = statusCode.value();
        this.messages = messages;
        this.success = success;
        this.data = data;

    }

    public static ResponseEntity<Response<Void>> failedResponse(HttpStatus statusCode, List<String> messages) {
        final var response = new Response<Void>(statusCode, messages, false, null);
        return ResponseEntity.status(statusCode).body(response);
    }

    public static <T> ResponseEntity<Response<T>> successResponse(HttpStatus statusCode, T data) {
        final var response = new Response<>(statusCode, null, true, data);
        return ResponseEntity.status(statusCode).body(response);
    }

    public static <T> ResponseEntity<Response<T>> successResponse(HttpStatus statusCode) {
        final var response = new Response<T>(statusCode, null, true, null);
        return ResponseEntity.status(statusCode).body(response);
    }


}
