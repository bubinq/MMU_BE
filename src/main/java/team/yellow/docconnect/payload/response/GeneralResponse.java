package team.yellow.docconnect.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralResponse {
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean last;
}
