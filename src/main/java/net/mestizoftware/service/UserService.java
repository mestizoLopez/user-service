package net.mestizoftware.service;


import net.mestizoftware.dto.UserDTO;
import net.mestizoftware.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(String userId);

}
