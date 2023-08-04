package team.yellow.docconnect.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team.yellow.docconnect.entity.State;
import team.yellow.docconnect.exception.ResourceNotFoundException;
import team.yellow.docconnect.payload.dto.StateDto;
import team.yellow.docconnect.payload.mapper.StateMapper;
import team.yellow.docconnect.payload.response.StateResponse;
import team.yellow.docconnect.repository.StateRepository;
import team.yellow.docconnect.service.StateService;

import java.util.List;

@Service
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public StateDto createState(StateDto stateDto) {
        State stateToCreate = StateMapper.INSTANCE.dtoToEntity(stateDto);
        return StateMapper.INSTANCE.entityToDTO(stateRepository.save(stateToCreate));
    }

    @Override
    public StateDto getStateById(Long stateId) {
       State foundState = stateRepository.findById(stateId)
               .orElseThrow(() -> new ResourceNotFoundException("State", "Id", stateId));
       return StateMapper.INSTANCE.entityToDTO(foundState);
    }

    @Override
    public StateResponse getAllStates(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<State> countries = stateRepository.findAll(pageable);
        return getResponse(countries);
    }

    @Override
    public StateDto updateStateById(StateDto stateDto, Long stateId) {
        State foundState = stateRepository.findById(stateId)
                .orElseThrow(() -> new ResourceNotFoundException("State", "Id", stateId));
        foundState.setName(stateDto.name());
        return StateMapper.INSTANCE.entityToDTO(stateRepository.save(foundState));
    }

    @Override
    public void deleteStateId(Long stateId) {
        State foundState = stateRepository.findById(stateId)
                .orElseThrow(() -> new ResourceNotFoundException("State", "Id", stateId));
        stateRepository.delete(foundState);
    }

    private StateResponse getResponse(Page<State> countries) {
        List<StateDto> content = StateMapper.INSTANCE.entityToDTO(countries.getContent());
        StateResponse stateResponse = new StateResponse(content);
        stateResponse.setPageNo(countries.getNumber());
        stateResponse.setLast(countries.isLast());
        stateResponse.setTotalPages(countries.getTotalPages());
        stateResponse.setPageSize(countries.getSize());
        stateResponse.setTotalElements(countries.getTotalElements());
        return stateResponse;
    }
}
