package constraints;

import entity.ExternalVariable;
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
public class ConstraintExternalToInput {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "constraint_on_input_id")
    private Input constraintOn;

    @OneToMany
    @JoinColumn(name = "ext_var")
    private Set<ExternalVariable> externalVariable;

}
