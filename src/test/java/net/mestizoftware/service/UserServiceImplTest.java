package net.mestizoftware.service;

import net.mestizoftware.dto.UserDTO;
import net.mestizoftware.entity.User;
import net.mestizoftware.exception.UserNotFoundException;
import net.mestizoftware.repository.UserRepository;
import net.mestizoftware.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setup(){

        user = User.builder()
            .userId(UUID.randomUUID().toString())
            .name("Javier")
            .email("ing.javierlg@gmail.com")
            .information("Some information")
            .build();
    }

    @Test
    void saveUserTest(){
        when(userRepository.save(any(User.class))).thenReturn(user);
        userService.saveUser(new User());

        //assertThat(created.getUserId()).isNotEmpty();
    }

    @Test
    void getAllUsersTest() {
        List<User> users = List.of(user);
        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> actual = userService.getAllUsers();

        assertThat(actual).hasSize(1);
    }

    @Test
    void getUserTest(){
        when(userRepository.findById(any(String.class))).thenReturn(Optional.of(user));

        //UserDTO expected = userDTOMapper.apply(user);
        UserDTO actual = userService.getUserById("12345abcd");

        assertThat(actual.name()).isEqualTo("Javier");
    }

    @Test
    void userNotFoundTest() {
        assertThatThrownBy(() -> userService.getUserById("12345abcd"))
            .isInstanceOf(UserNotFoundException.class);
    }

}
