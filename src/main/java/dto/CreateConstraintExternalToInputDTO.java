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
public class CreateConstraintExternalToInputDTO {

    private Integer constraintOn;

    private Set<Integer> externalVariablesIds;
}
