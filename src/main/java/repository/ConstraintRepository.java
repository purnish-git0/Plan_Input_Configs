package repository;

import constraints.SimpleConstraint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstraintRepository extends JpaRepository<SimpleConstraint, Integer> {
}
