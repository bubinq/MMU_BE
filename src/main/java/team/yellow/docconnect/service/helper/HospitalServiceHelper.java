package team.yellow.docconnect.service.helper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import team.yellow.docconnect.entity.Hospital;
import team.yellow.docconnect.payload.dto.HospitalDto;
import team.yellow.docconnect.payload.mapper.HospitalMapper;
import team.yellow.docconnect.payload.response.HospitalResponse;

import java.util.List;

@Component
public class HospitalServiceHelper {

    public HospitalResponse getHospitalResponse(Page<Hospital> allHospitals) {
        List<HospitalDto> content = HospitalMapper.INSTANCE.entityToDTO(allHospitals.getContent());
        HospitalResponse hospitalResponse = new HospitalResponse(content);
        hospitalResponse.setPageNo(allHospitals.getNumber());
        hospitalResponse.setLast(allHospitals.isLast());
        hospitalResponse.setTotalPages(allHospitals.getTotalPages());
        hospitalResponse.setPageSize(allHospitals.getSize());
        hospitalResponse.setTotalElements(allHospitals.getTotalElements());
        return hospitalResponse;
    }
}
