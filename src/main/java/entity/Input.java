package entity;

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



}
