package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class FrontEndConstraintsDTO {

    private Integer constraintOn;

    private Set<Integer> constrainedBy;




}
