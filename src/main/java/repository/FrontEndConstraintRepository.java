package repository;

import constraints.FrontEndConstraintImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrontEndConstraintRepository extends JpaRepository<FrontEndConstraintImpl, Integer> {
}
