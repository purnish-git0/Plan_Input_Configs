package entity;

import constraints.ConstraintExternalToInput;
import constraints.FrontEndConstraintImpl;
import constraints.SimpleConstraint;
import interfaces.ExternalConstraint;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Externalizable;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Input {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    private String type;


    @ManyToMany
    @JoinTable(name = "inputs_plans",
    joinColumns = {@JoinColumn(name = "plan_id")},
    inverseJoinColumns = {@JoinColumn(name = "input_id")})
    private Set<Plan> plans;

    @OneToMany(mappedBy = "constraintOn")
    private Set<SimpleConstraint> constraints;

    @OneToMany(mappedBy = "constraintOn")
    private Set<ConstraintExternalToInput> constraintsExternalToInput;

    @OneToMany
    @JoinColumn(name = "front_end_constraints_id")
    private Set<FrontEndConstraintImpl> frontEndConstraints;
}
