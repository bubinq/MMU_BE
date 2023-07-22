package team.yellow.docconnect.payload.response;

import team.yellow.docconnect.payload.dto.UserDto;

import java.util.List;

public record UserResponse (

        List<UserDto> content,
        int pageNo,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean last
) {
}
