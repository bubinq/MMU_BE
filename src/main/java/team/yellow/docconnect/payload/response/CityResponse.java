package team.yellow.docconnect.payload.response;

import team.yellow.docconnect.payload.dto.CityDto;

import java.util.List;

public record CityResponse (
        List<CityDto> content,
        int pageNo,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean last
)
{}
