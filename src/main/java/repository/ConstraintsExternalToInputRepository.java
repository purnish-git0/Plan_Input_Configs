package repository;

import constraints.ConstraintExternalToInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstraintsExternalToInputRepository extends JpaRepository<ConstraintExternalToInput, Integer> {
}
