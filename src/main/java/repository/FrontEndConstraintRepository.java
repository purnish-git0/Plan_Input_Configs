package repository;

import constraints.FrontEndConstraintImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrontEndConstraintRepository extends JpaRepository<FrontEndConstraintImpl, Integer> {

    @Query(value = "select c from FrontEndConstraintImpl fc where fc.constraintOn.id=:inputId")
    public List<FrontEndConstraintImpl> getFrontEndConstraintsForInput(@Param("inputId") Integer inputId);



}
