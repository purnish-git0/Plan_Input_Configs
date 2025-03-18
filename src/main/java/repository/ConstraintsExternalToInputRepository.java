package repository;

import constraints.ConstraintExternalToInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstraintsExternalToInputRepository extends JpaRepository<ConstraintExternalToInput, Integer> {


    @Query(value = "select c from ConstraintExternalToInput c where c.constraintOn.id=:inputId")
    List<ConstraintExternalToInput> getExternalVariableConstraintsForInput(@Param("inputId") Integer inputId);

}
