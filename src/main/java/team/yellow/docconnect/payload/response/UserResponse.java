package team.yellow.docconnect.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.yellow.docconnect.payload.dto.UserDto;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends GeneralResponse{
    private   List<UserDto> content;
}
