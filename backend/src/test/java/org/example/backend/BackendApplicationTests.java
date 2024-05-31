package org.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

// This will not start the server, but will start the application context
//@SpringBootTest()
// This will start the server
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BackendApplicationTests {
    // This will inject the port that the server is running on
    @LocalServerPort
    private Integer port;
    @Test
    void contextLoads() {
    }

}
