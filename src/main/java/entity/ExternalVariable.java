package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ExternalVariable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToMany
    @JoinTable(name = "external_variable_plan",
    joinColumns = {@JoinColumn(name = "plan_id")},
    inverseJoinColumns = {@JoinColumn(name = "external_variable_id")})
    private Plan plan;

    private String name;

    private String type;

    private String domain;


}
