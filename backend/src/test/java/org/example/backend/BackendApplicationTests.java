package org.example.backend;

import org.example.backend.app.AbstractUserApp;
import org.example.backend.domain.AbstractUser;
import org.example.backend.domain.Admin;
import org.example.backend.domain.Permission;
import org.example.backend.domain.User;
import org.example.backend.service.AbstractUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// This will not start the server, but will start the application context
//@SpringBootTest()
// This will start the server
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class BackendApplicationTests {
    // This will inject the port that the server is running on
    @LocalServerPort
    private Integer port;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private AbstractUserService abstractUserService;

    @Test
    void contextLoads() {
    }

    @Test
    void userTest() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setName("admin");
        admin.setBio("admin");
        abstractUserService.saveUser(admin);
        for (int i = 0; i < 8; i++) {
            User user = new User();
            user.setUsername(String.valueOf(i));
            user.setPassword(String.valueOf(i));
            user.setName("user" + i);
            user.setBio("user" + i);
            abstractUserService.saveUser(user);
        }
    }

    @Test
    void MessageTest() {
        // preform login
        String token1 = restTemplate.postForObject("http://localhost:" + port + "/login?username=1&password=1", null, String.class);
        String token2 = restTemplate.postForObject("http://localhost:" + port + "/login?username=2&password=2", null, String.class);
        // test the sendChat api
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/message/sendChat").header("Authorization", token1)
                    .param("userId", "2")
                    .param("content", "hello")
                    .param("time", "2021-05-01 00:00:00")).andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
