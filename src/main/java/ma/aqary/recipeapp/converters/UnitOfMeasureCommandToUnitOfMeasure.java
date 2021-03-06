package ma.aqary.recipeapp.converters;

import lombok.Synchronized;
import ma.aqary.recipeapp.commands.UnitOfMeasureCommand;
import ma.aqary.recipeapp.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Hamid Barakat
 */
@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Nullable
    @Synchronized
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
       if (unitOfMeasureCommand==null){
        return null;
       }
       final UnitOfMeasure uom = new UnitOfMeasure();
       uom.setId(unitOfMeasureCommand.getId());
       uom.setDescription(unitOfMeasureCommand.getDescription());
       return  uom;
    }
}