package ma.aqary.recipeapp.services;

import ma.aqary.recipeapp.commands.UnitOfMeasureCommand;
import ma.aqary.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import ma.aqary.recipeapp.domain.UnitOfMeasure;
import ma.aqary.recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Hamid Barakat
 */
class UnitOfMeasureServiceImplTest {
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

     UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand=new UnitOfMeasureToUnitOfMeasureCommand();
     UnitOfMeasureService unitOfMeasureService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        unitOfMeasureService=new UnitOfMeasureServiceImpl(unitOfMeasureRepository,unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    void listAllUomsTest() {
        //Given
        Set<UnitOfMeasure> unitOfMeasures=new HashSet<>();
        UnitOfMeasure unitOfMeasure=new UnitOfMeasure();
        unitOfMeasure.setId(1L);
        unitOfMeasures.add(unitOfMeasure);
        UnitOfMeasure unitOfMeasure2=new UnitOfMeasure();
        unitOfMeasure2.setId(2L);
        unitOfMeasures.add(unitOfMeasure2);
        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);
        //When
        Set<UnitOfMeasureCommand> commands=unitOfMeasureService.listAllUoms();
        //Then
        assertEquals(2,commands.size());
        verify(unitOfMeasureRepository,times(1)).findAll();
    }
}