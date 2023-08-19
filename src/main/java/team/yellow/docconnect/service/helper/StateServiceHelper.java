package team.yellow.docconnect.service.helper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import team.yellow.docconnect.entity.State;
import team.yellow.docconnect.payload.dto.StateDto;
import team.yellow.docconnect.payload.mapper.StateMapper;
import team.yellow.docconnect.payload.response.StateResponse;

import java.util.List;

@Component
public class StateServiceHelper {

    public StateResponse getStateResponse(Page<State> states) {
        List<StateDto> content = StateMapper.INSTANCE.entityToDTO(states.getContent());
        StateResponse stateResponse = new StateResponse(content);
        stateResponse.setPageNo(states.getNumber());
        stateResponse.setLast(states.isLast());
        stateResponse.setTotalPages(states.getTotalPages());
        stateResponse.setPageSize(states.getSize());
        stateResponse.setTotalElements(states.getTotalElements());
        return stateResponse;
    }
}
