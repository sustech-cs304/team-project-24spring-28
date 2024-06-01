package org.example.backend;

import org.example.backend.app.AbstractUserApp;
import org.example.backend.domain.AbstractUser;
import org.example.backend.domain.Admin;
import org.example.backend.domain.Permission;
import org.example.backend.domain.User;
import org.example.backend.dto.GlobalResponse;
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
            Permission permission = new Permission();
            permission.setUser(user);
            permission.setCanCreate((i & 1) == 1);
            permission.setCanEnroll((i & 2) == 2);
            permission.setCanComment((i & 4) == 4);
            user.setPermission(permission);
            abstractUserService.saveUser(user);
        }
    }

    @Test
    void LoginTest() {
        String token = restTemplate.postForObject("http://localhost:" + port + "/login?username=1&password=1", null, String.class);
    }

    @Test
    void MessageTest() {
        // preform login
        String token1 = (String) restTemplate.postForObject("http://localhost:" + port + "/login?username=1&password=1", null, GlobalResponse.class).getData();
        String token2 = (String) restTemplate.postForObject("http://localhost:" + port + "/login?username=2&password=2", null, GlobalResponse.class).getData();
        // test the sendChat api
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/message/sendChat")
                    .header("Authorization", token1)
                    .param("userId", "2")
                    .param("content", "hello2")
                    .param("time", "2021-05-01 00:00:00")
            ).andExpect(MockMvcResultMatchers.status().isOk());
            mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/message/sendChat")
                    .header("Authorization", token2)
                    .param("userId", "1")
                    .param("content", "hello1")
                    .param("time", "2021-05-02 00:00:00")
            ).andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void EventTest() {
        // preform login
        String token = (String) restTemplate.postForObject("http://localhost:" + port + "/login?username=3&password=3", null, GlobalResponse.class).getData();
        // test the releaseEvent api
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/event/create")
                    .header("Authorization", token)
                    .param("title", "event1")
                    .param("name", "event-name1")
                    .param("applyStartTime", "2021-06-05 00:00:00")
                    .param("applyEndTime", "2021-06-14 00:00:00")
                    .param("startTime", "2021-06-15 00:00:00")
                    .param("endTime", "2021-06-16 00:00:00")
                    .param("introduction", "event-introduction1")
                    .param("imageUrl", "")
                    .param("mdText", "")
                    .param("enrollmentType", "count")
                    .param("limitCount", "100")
            ).andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void PostTest() {

    }

}
