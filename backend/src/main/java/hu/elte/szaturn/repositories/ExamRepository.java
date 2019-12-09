package hu.elte.szaturn.repositories;

import hu.elte.szaturn.entities.Exam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends CrudRepository<Exam, Integer> {

}
