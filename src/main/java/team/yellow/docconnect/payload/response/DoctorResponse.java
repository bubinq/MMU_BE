package team.yellow.docconnect.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.yellow.docconnect.payload.dto.DoctorDto;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse extends GeneralResponse{
  private List<DoctorDto> content;
}
