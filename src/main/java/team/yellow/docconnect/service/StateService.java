package team.yellow.docconnect.service;

import team.yellow.docconnect.payload.dto.StateDto;
import team.yellow.docconnect.payload.response.StateResponse;

public interface StateService {
    StateDto createState(StateDto stateDto);
    StateDto getStateById(Long stateId);
    StateResponse getAllStates(int pageNo, int pageSize, String sortBy, String sortDir);
    StateDto updateStateById(StateDto stateDto, Long stateId);
    void deleteStateId(Long stateId);
}
