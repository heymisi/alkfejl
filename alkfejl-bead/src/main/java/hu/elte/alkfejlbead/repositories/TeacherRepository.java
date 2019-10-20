
package hu.elte.alkfejlbead.repositories;

import hu.elte.alkfejlbead.entities.Teacher;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
    Optional<Teacher> findByNeptunCode(String neptunCode);
}
