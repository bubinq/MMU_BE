package team.yellow.docconnect.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team.yellow.docconnect.entity.User;
import team.yellow.docconnect.exception.ResourceNotFoundException;
import team.yellow.docconnect.payload.dto.UserDto;
import team.yellow.docconnect.payload.mapper.UserMapper;
import team.yellow.docconnect.payload.response.UserResponse;
import team.yellow.docconnect.repository.UserRepository;
import team.yellow.docconnect.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.dtoToEntity(userDto);
        return UserMapper.INSTANCE.entityToDTO(userRepository.save(user));
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return UserMapper.INSTANCE.entityToDTO(user);
    }

    @Override
    public UserResponse getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<User> allUsers = userRepository.findAll(pageable);
        return getUserResponse(allUsers);
    }

    @Override
    public UserDto updateUserById(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setFirst_name(userDto.first_name());
        user.setLast_name(userDto.last_name());
        user.setEmail(userDto.email());
        user.setPassword(userDto.password());
        user.setVerified(userDto.verified());
        return UserMapper.INSTANCE.entityToDTO(userRepository.save(user));
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
    }

    private UserResponse getUserResponse(Page<User> allUsers) {
        List<UserDto> content = UserMapper.INSTANCE.entityToDTO(allUsers.getContent());
        UserResponse userResponse = new UserResponse(content);
        userResponse.setPageNo(allUsers.getNumber());
        userResponse.setLast(allUsers.isLast());
        userResponse.setTotalPages(allUsers.getTotalPages());
        userResponse.setPageSize(allUsers.getSize());
        userResponse.setTotalElements(allUsers.getTotalElements());
        return userResponse;
    }
}
