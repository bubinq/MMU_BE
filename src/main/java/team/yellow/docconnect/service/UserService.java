package team.yellow.docconnect.service;

import team.yellow.docconnect.payload.dto.UserDto;
import team.yellow.docconnect.payload.response.UserResponse;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    UserResponse getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir);

    UserDto updateUserById(Long id, UserDto userDto);

    void deleteUserById(Long id);
}
