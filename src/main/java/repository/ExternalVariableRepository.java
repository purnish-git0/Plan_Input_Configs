package repository;

import entity.ExternalVariable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalVariableRepository extends JpaRepository<ExternalVariable, Integer> {


}
