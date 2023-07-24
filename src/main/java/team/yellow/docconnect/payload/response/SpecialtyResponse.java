package team.yellow.docconnect.payload.response;

import team.yellow.docconnect.payload.dto.SpecialtyDto;

import java.util.List;


public record SpecialtyResponse (
        List<SpecialtyDto> content,
        int pageNo,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean last
) {
}
