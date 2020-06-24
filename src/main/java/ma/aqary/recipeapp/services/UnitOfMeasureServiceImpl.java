package ma.aqary.recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import ma.aqary.recipeapp.commands.UnitOfMeasureCommand;
import ma.aqary.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import ma.aqary.recipeapp.domain.UnitOfMeasure;
import ma.aqary.recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Hamid Barakat
 */
@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {

        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(), false)
                .map(unitOfMeasureToUnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
    }


}
