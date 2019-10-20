
package hu.elte.alkfejlbead.repositories;

import hu.elte.alkfejlbead.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {
    
}
