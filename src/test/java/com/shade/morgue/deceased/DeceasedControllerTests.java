package com.shade.morgue.deceased;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
public class DeceasedControllerTests {

    @Autowired
    private RestTestClient restTestClient;

    @Test
    void shouldCreateNewDeceased() {
        Deceased newDeceased = new Deceased("BB0001");

        restTestClient.post()
                .uri("/deceased")
                .body(newDeceased)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void shouldReturnDeceasedAfterCreation() {
        Deceased newDeceased = new Deceased("BB0002");

        UUID id = restTestClient.post()
                .uri("/deceased")
                .body(newDeceased)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(DeceasedResponse.class)
                .returnResult()
                .getResponseBody()
                .id();

        restTestClient.get()
                .uri("/deceased/{id}", id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(DeceasedResponse.class)
                .value(response -> {
                    assertThat(response.id()).isEqualTo(id);
                    assertThat(response.deceasedId()).isEqualTo("BB0002");
                });


    }
}
