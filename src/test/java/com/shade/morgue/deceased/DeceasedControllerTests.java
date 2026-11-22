package com.shade.morgue.deceased;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

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
}
