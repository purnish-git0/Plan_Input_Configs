package constraints;

import entity.Input;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class SimpleConstraint {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "constraint_on_id")
    private Input constraintOn;

    @OneToMany
    @JoinColumn(name = "constrained_by_input")
    private Set<Input> constrainedBy;


}
