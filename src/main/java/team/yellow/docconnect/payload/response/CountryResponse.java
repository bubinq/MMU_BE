package team.yellow.docconnect.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.yellow.docconnect.payload.dto.CountryDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryResponse {
      private   List<CountryDto> content;
      private int pageNo;
      private int pageSize;
      private Long totalElements;
      private int totalPages;
      private boolean last;
}
