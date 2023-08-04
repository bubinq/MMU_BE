package team.yellow.docconnect.payload.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import team.yellow.docconnect.entity.State;
import team.yellow.docconnect.payload.dto.StateDto;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StateMapper {

    StateMapper INSTANCE = Mappers.getMapper(StateMapper.class);
    StateDto entityToDTO(State state);

    List<StateDto> entityToDTO(Iterable<State> countries);

    State dtoToEntity(StateDto stateDto);

    List<State> dtoToEntity(Iterable<StateDto> countryDtos);

}
