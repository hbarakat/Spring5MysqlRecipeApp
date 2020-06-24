package ma.aqary.recipeapp.repositories;

import ma.aqary.recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Hamid Barakat
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByDescription(String description);
}
