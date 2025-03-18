package constraints;

import entity.Input;
import interfaces.FrontEndConstraint;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class FrontEndConstraintImpl implements FrontEndConstraint  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "constraint_on_input_id")
    private Input constraintOn;

    @OneToMany
    @JoinColumn(name = "id")
    private Set<Input> constrainedByInputs;


    @Override
    public Set<Input> getConstraintsOn() {
        return Set.of();
    }
}
