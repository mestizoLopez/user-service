package net.mestizoftware.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.mestizoftware.dto.UserDTO;
import net.mestizoftware.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDTO userDTO;

    @BeforeEach
    void setup() {
        userDTO = new UserDTO(
            UUID.randomUUID().toString(),
            "Javier",
            "ing.javierlg@gmail.com",
            "Some information"
        );
    }


    @Test
    void getUserByIdTest() throws Exception {

        when(userService.getUserById("1any")).thenReturn(userDTO);

        ResultActions response = mockMvc.perform(get("/user/1any")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userDTO))
        );

        response.andExpect(status().isOk());
    }

    @Test
    void getAllUsers() throws Exception {
        List<UserDTO> users = List.of(userDTO);

        when(userService.getAllUsers()).thenReturn(users);

        ResultActions response = mockMvc.perform(get("/user")
            .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isOk());
    }

    @Test
    void saveUserTest() {

    }

}
