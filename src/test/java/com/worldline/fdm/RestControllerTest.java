package com.worldline.fdm;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = FdmApplication.class)
public class RestControllerTest {
    private static HttpClient client;
    private static URI url;

    @BeforeAll
    static void init(){
        client = HttpClient.newBuilder().build();
    }

    @Test
    void ok_getFilteredFlightData() throws IOException, InterruptedException {
        url = URI.create("http://localhost:8080/v1/flights");
        String data = "{\n" +
                "    \"airline\": \"a\",\n" +
                "    \"departure\": \"ATL\",\n" +
                "    \"destination\": \"AMS\",\n" +
                "    \"departureDateTime\": \"2025-06-20T00:00:00Z\",\n" +
                "    \"arrivalDateTime\": \"2025-08-05T00:00:00Z\"\n" +
                "}";
        HttpRequest request = HttpRequest.newBuilder().uri(url).POST(HttpRequest.BodyPublishers.ofString(data))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
    }

    @Test
    void badData_getFilteredFlightData() throws IOException, InterruptedException {
        url = URI.create("http://localhost:8080/v1/flights");
        String data = "{\n" +
                "    \"airline\": \"a\",\n" +
                "    \"departure\": \"ATLs\",\n" +
                "    \"destination\": \"AMS\",\n" +
                "    \"departureDateTime\": \"2025-06-20T00:00:00Z\",\n" +
                "    \"arrivalDateTime\": \"2025-08-05T00:00:00Z\"\n" +
                "}";
        HttpRequest request = HttpRequest.newBuilder().uri(url).POST(HttpRequest.BodyPublishers.ofString(data))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
        assertTrue(response.body().contains("The length of departure must be 3 characters."));
    }

    @Test
    void ok_addFlightData() throws IOException, InterruptedException {
        url = URI.create("http://localhost:8080/v1/flight");
        String data = "{\n" +
                "            \"airline\": \"Beta Airlines\",\n" +
                "            \"supplier\": \"Airline supplier 2\",\n" +
                "            \"price\": 300.0,\n" +
                "            \"departure\": \"ATL\",\n" +
                "            \"destination\": \"AMS\",\n" +
                "            \"departureDateTime\":\"2025-08-01T19:07:00Z\",\n" +
                "            \"arrivalDateTime\": \"2025-08-01T19:07:00Z\"\n" +
                "        }";
        HttpRequest request = HttpRequest.newBuilder().uri(url).POST(HttpRequest.BodyPublishers.ofString(data))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(201, response.statusCode());
    }

    @Test
    void ok_Update_DeleteFlightData() throws IOException, InterruptedException {
        url = URI.create("http://localhost:8080/v1/flight/1");
        String data = "{\n" +
                "            \"id\": 1,\n" +
                "            \"airline\": \"Beta Airlines\",\n" +
                "            \"supplier\": \"Airline supplier 2\",\n" +
                "            \"price\": 3040.0,\n" +
                "            \"departure\": \"ATL\",\n" +
                "            \"destination\": \"AMS\",\n" +
                "            \"departureDateTime\":\"2025-08-01T19:07:00Z\",\n" +
                "            \"arrivalDateTime\": \"2025-08-01T19:07:00Z\"\n" +
                "        }";
        HttpRequest request = HttpRequest.newBuilder().uri(url).method("PATCH", HttpRequest.BodyPublishers.ofString(data))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());


        url = URI.create("http://localhost:8080/v1/flight/1");
        HttpRequest request2 = HttpRequest.newBuilder().uri(url).DELETE()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response2 = client.send(request2, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
    }

}
