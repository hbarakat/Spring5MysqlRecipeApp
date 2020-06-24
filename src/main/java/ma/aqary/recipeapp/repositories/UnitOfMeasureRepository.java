package ma.aqary.recipeapp.repositories;

import ma.aqary.recipeapp.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Hamid Barakat
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    Optional<UnitOfMeasure> findByDescription(String description);

}
