package ma.aqary.recipeapp.repositories;

import ma.aqary.recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Hamid Barakat
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {
@Autowired
UnitOfMeasureRepository unitOfMeasureRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    @DirtiesContext
    void findByDescription() {
        Optional<UnitOfMeasure> unitOfMeasure=unitOfMeasureRepository.findByDescription("Gr");
        assertEquals("Gr",unitOfMeasure.get().getDescription());
    }
    @Test
    void findByDescription2() {
        Optional<UnitOfMeasure> unitOfMeasure=unitOfMeasureRepository.findByDescription("Ml");
        assertEquals("Ml",unitOfMeasure.get().getDescription());
    }
}