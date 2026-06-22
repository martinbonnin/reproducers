package com.example;

import com.apollographql.java.client.ApolloClient;
import mockwebserver3.MockResponse;
import mockwebserver3.MockWebServer;
import okhttp3.Headers;
import org.junit.Test;

import java.io.IOException;

public class MainTest {
    @Test
    public void test() throws IOException {

        try(MockWebServer mockServer = new MockWebServer()) {
            mockServer.start();
            mockServer.enqueue(new MockResponse(200, Headers.EMPTY, "{\"data\": {\"foo\": \"bar\"}}"));

            try (ApolloClient apolloClient = new ApolloClient.Builder()
                    .serverUrl(mockServer.url("/").toString())
                    .build()) {

                apolloClient.query(new GetFooQuery()).enqueue(System.out::println);
            }
        }
    }
}
