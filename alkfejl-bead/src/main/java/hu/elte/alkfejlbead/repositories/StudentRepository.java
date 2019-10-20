
package hu.elte.alkfejlbead.repositories;

import hu.elte.alkfejlbead.entities.Student;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    Optional<Student> findByNeptunCode(String neptunCode);
}
