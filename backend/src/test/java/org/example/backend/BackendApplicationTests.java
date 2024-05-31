package org.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

// This will not start the server, but will start the application context
//@SpringBootTest()
// This will start the server
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BackendApplicationTests {
    // This will inject the port that the server is running on
    @LocalServerPort
    private Integer port;
    @Test
    void contextLoads() {
    }

}
