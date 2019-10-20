
package hu.elte.alkfejlbead.repositories;

import hu.elte.alkfejlbead.entities.Exam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends CrudRepository<Exam, Integer> {
    
}
