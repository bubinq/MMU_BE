package team.yellow.docconnect.payload.response;

import team.yellow.docconnect.payload.dto.CountryDto;

import java.util.List;

public record CountryResponse
        (
                List<CountryDto> content,
                int pageNo,
                int pageSize,
                long totalElements,
                int totalPages,
                boolean last
        ) {}
