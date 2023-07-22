package team.yellow.docconnect.payload.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import team.yellow.docconnect.entity.User;
import team.yellow.docconnect.payload.dto.UserDto;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto entityToDTO(User user);

    List<UserDto> entityToDTO(Iterable<User> users);

    User dtoToEntity(UserDto userDto);
}
