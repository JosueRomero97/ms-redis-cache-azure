package com.tdp.ms.msredis.expose;

import com.tdp.genesis.core.constants.HttpHeadersKey;
import com.tdp.ms.msredis.business.MsredisService;
import com.tdp.ms.msredis.model.MsredisRequest;
import com.tdp.ms.msredis.model.MsredisResponse;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.mockito.Mockito.mock;

public class MsredisControllerTest {

    private static final String POST = "post";
    private static final String GENESIS = "genesis";
    private static final String UUID = "550e8400-e29b-41d4-a716-446655440000";

    private static WebTestClient webClient;

    @Mock
    private static MsredisService msredisService;

    private static Map<String, MsredisResponse> msredisResponseMap = new HashMap<>();
    private static Map<String, MsredisRequest> msredisRequestMap = new HashMap<>();

    @BeforeAll
    public static void setUp() {
        msredisService = mock(MsredisService.class);
        webClient = WebTestClient.bindToController(new MsredisController(msredisService))
                .configureClient()
                .baseUrl("/msredis/v1/greeting")
                .defaultHeader(HttpHeadersKey.UNICA_PID,UUID)
                .defaultHeader(HttpHeadersKey.UNICA_SERVICE_ID,UUID)
                .defaultHeader(HttpHeadersKey.UNICA_USER,GENESIS)
                .defaultHeader(HttpHeadersKey.UNICA_APPLICATION,GENESIS)
                .build();

        msredisResponseMap.put("get", new MsredisResponse("Hello world!"));
        msredisResponseMap.put(POST, new MsredisResponse("User created!"));
        msredisRequestMap.put(POST, new MsredisRequest("User"));
        msredisRequestMap.put("empty", new MsredisRequest(""));
    }

    @Test
    public void indexGetTest() {

        Mockito.when(msredisService.get())
                .thenReturn(new MsredisResponse("Hello world!"));

        this.webClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .header("ClientId","12122322")
                .exchange()
                .expectStatus().isOk()
                .expectBody(MsredisResponse.class)
                .isEqualTo(new MsredisResponse("Hello world!"));
    }

    @Test
    public void indexPostTest() {

        Mockito.when(msredisService.put("User"))
                .thenReturn(new MsredisResponse("User created!"));

        this.webClient.put()
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(msredisRequestMap.get(POST)))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.METHOD_NOT_ALLOWED)
                .expectBody(MsredisResponse.class)
                .isEqualTo(null);
    }

}
