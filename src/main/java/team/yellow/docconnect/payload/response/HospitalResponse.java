package team.yellow.docconnect.payload.response;

import team.yellow.docconnect.payload.dto.HospitalDto;

import java.util.List;

public record HospitalResponse(

        List<HospitalDto> content,
        int pageNo,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean last
) {
}
