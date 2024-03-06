package net.mestizoftware.service.impl;

import net.mestizoftware.dto.UserDTO;
import net.mestizoftware.entity.User;
import net.mestizoftware.exception.UserNotFoundException;
import net.mestizoftware.repository.UserRepository;
import net.mestizoftware.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        userRepository.save(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
            .stream()
            .map(userMapper)
            .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(String userId) {

        return userRepository.findById(userId)
            .map(userMapper)
            .orElseThrow(
            () -> new UserNotFoundException("User "+  userId +" Not Found"));
    }

    private final Function<User,UserDTO> userMapper = user -> new UserDTO(
        user.getUserId(),
        user.getName(),
        user.getEmail(),
        user.getInformation()
    );
}
