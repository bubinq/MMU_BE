package team.yellow.docconnect.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.yellow.docconnect.payload.dto.StateDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StateResponse extends GeneralResponse{
      private List<StateDto> content;

}
